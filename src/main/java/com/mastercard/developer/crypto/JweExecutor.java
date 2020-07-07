package com.mastercard.developer.crypto;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.spi.json.JsonProvider;
import com.mastercard.developer.encryption.EncryptionException;
import com.mastercard.developer.json.JsonEngine;
import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEDecrypter;
import com.nimbusds.jose.JWEEncrypter;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.crypto.RSAEncrypter;

import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Performs JWEEncrypter and JWEDecrypter operations on HTTP payloads.
 * See: https://tools.ietf.org/html/rfc7516 for more details.
 */
public class JweExecutor {

    private static final String JSON_PATH_IN_OUT = "$";
    private static final String ENCRYPTED_VALUE_FIELD_NAME = "encryptedPayload";

    private static JsonEngine jsonEngine;
    private static Configuration jsonPathConfig = withJsonEngine(JsonEngine.getDefault());

    private JweExecutor() {
    }

    /**
     * Specify the JSON engine to be used.
     *
     * @param jsonEngine A {@link JsonEngine} instance
     */
    public static synchronized Configuration withJsonEngine(JsonEngine jsonEngine) {
        JweExecutor.jsonEngine = jsonEngine;
        JweExecutor.jsonPathConfig = new Configuration.ConfigurationBuilder()
                .jsonProvider(jsonEngine.getJsonProvider())
                .options(Option.SUPPRESS_EXCEPTIONS)
                .build();
        return jsonPathConfig;
    }

    /**
     * Encrypt parts of a payload using the given configuration.
     *
     * @param payload   A plain text
     * @param jweConfig A {@link com.mastercard.developer.crypto.JweConfig} instance
     * @return The encrypted payload
     * @throws EncryptionException If error occurred while encrypting payload.
     */
    public static String encryptPayload(String payload, JweConfig jweConfig) throws EncryptionException {
        try {
            // Parse the given payload
            DocumentContext payloadContext = JsonPath.parse(payload, jsonPathConfig);
            jweEncryptPayloadPath(payloadContext, JSON_PATH_IN_OUT, JSON_PATH_IN_OUT, jweConfig);

            // Return the updated payload
            return payloadContext.jsonString();
        } catch (JOSEException ex) {
            throw new EncryptionException("Payload encryption failed!", ex);
        }
    }

    /**
     * Decrypt parts of a response payload using the given configuration.
     *
     * @param payload   A ciphertext
     * @param jweConfig A {@link com.mastercard.developer.crypto.JweConfig} instance
     * @return The decrypted payload
     * @throws EncryptionException If error occurred while decrypting payload.
     */
    public static String decryptPayload(String payload, JweConfig jweConfig) throws EncryptionException {
        try {
            // Parse the given payload
            DocumentContext payloadContext = JsonPath.parse(payload, jsonPathConfig);
            jweDecryptPayloadPath(payloadContext, JSON_PATH_IN_OUT, JSON_PATH_IN_OUT, jweConfig);

            // Return the updated payload
            return payloadContext.jsonString();
        } catch (ParseException | JOSEException ex) {
            throw new EncryptionException("Payload decryption failed!", ex);
        }
    }

    private static void jweEncryptPayloadPath(DocumentContext payloadContext, String jsonPathIn, String jsonPathOut, JweConfig jweConfig) throws JOSEException {

        Object inJsonElement = readJsonElement(payloadContext, jsonPathIn);
        if (inJsonElement == null) {
            // Nothing to encrypt
            return;
        }

        // Encrypt data at the given JSON path
        String inJsonString = sanitizeJson(jsonEngine.toJsonString(inJsonElement));
        String encryptedPayload = jweEncrypt(inJsonString, jweConfig);

        // Delete keys one by one
        Collection<String> propertyKeys = new ArrayList<>(jsonEngine.getPropertyKeys(inJsonElement));
        propertyKeys.forEach(key -> payloadContext.delete(jsonPathIn + "." + key));

        // Add encrypted data and encryption fields at the given JSON path
        checkOrCreateOutObject(payloadContext, jsonPathOut);
        payloadContext.put(jsonPathOut, ENCRYPTED_VALUE_FIELD_NAME, encryptedPayload);
    }

    private static void jweDecryptPayloadPath(DocumentContext payloadContext, String jsonPathIn, String jsonPathOut, JweConfig jweConfig) throws ParseException, JOSEException {
        JsonProvider jsonProvider = jsonPathConfig.jsonProvider();
        Object inJsonObject = readJsonObject(payloadContext, jsonPathIn);
        if (inJsonObject == null) {
            // Nothing to decrypt
            return;
        }

        // Read and remove encrypted data and encryption fields at the given JSON path
        Object encryptedValueJsonElement = readAndDeleteJsonKey(payloadContext, jsonPathIn, inJsonObject, ENCRYPTED_VALUE_FIELD_NAME);
        if (jsonEngine.isNullOrEmptyJson(encryptedValueJsonElement)) {
            // Nothing to decrypt
            return;
        }

        // Decrypt data
        String decryptedPayload = jweDecrypt(jsonEngine.toJsonString(encryptedValueJsonElement), jweConfig);

        // Add decrypted data at the given JSON path
        decryptedPayload = sanitizeJson(decryptedPayload);
        checkOrCreateOutObject(payloadContext, jsonPathOut);
        addDecryptedDataToPayload(payloadContext, decryptedPayload, jsonPathOut);

        // Remove the input if now empty
        Object inJsonElement = readJsonElement(payloadContext, jsonPathIn);
        if (0 == jsonProvider.length(inJsonElement) && !JSON_PATH_IN_OUT.equals(jsonPathIn)) {
            payloadContext.delete(jsonPathIn);
        }
    }

    private static String jweEncrypt(String requestPayload, JweConfig jweConfig) throws JOSEException {
        RSAPublicKey rsaPublicKey = (RSAPublicKey) jweConfig.getEncryptionCertificate().getPublicKey();
        JWEEncrypter jweEncrypter = new RSAEncrypter(rsaPublicKey);

        JWEHeader jweHeader = new JWEHeader.Builder(JWEAlgorithm.RSA_OAEP_256, EncryptionMethod.A256GCM)
                .keyID(jweConfig.getEncryptionKeyFingerprint())
                .contentType("application/json")
                .build();
        JWEObject jweObject = new JWEObject(jweHeader, new Payload(requestPayload));
        jweObject.encrypt(jweEncrypter);

        return jweObject.serialize();
    }

    private static String jweDecrypt(String encryptedPayload, JweConfig jweConfig) throws ParseException, JOSEException {
        JWEDecrypter jweDecrypter = new RSADecrypter(jweConfig.getDecryptionKey());

        JWEObject jweObject = JWEObject.parse(encryptedPayload);
        jweObject.decrypt(jweDecrypter);

        return jweObject.getPayload().toString();
    }

    private static void addDecryptedDataToPayload(DocumentContext payloadContext, String decryptedValue, String jsonPathOut) {
        JsonProvider jsonProvider = jsonPathConfig.jsonProvider();
        Object decryptedValueJsonElement = jsonEngine.parse(decryptedValue);

        if (!jsonEngine.isJsonObject(decryptedValueJsonElement)) {
            // Array or primitive: overwrite
            payloadContext.set(jsonPathOut, decryptedValueJsonElement);
            return;
        }

        // Object: merge
        int length = jsonProvider.length(decryptedValueJsonElement);
        Collection<String> propertyKeys = (0 == length) ? Collections.<String>emptyList() : jsonProvider.getPropertyKeys(decryptedValueJsonElement);
        for (String key : propertyKeys) {
            payloadContext.delete(jsonPathOut + "." + key);
            payloadContext.put(jsonPathOut, key, jsonProvider.getMapValue(decryptedValueJsonElement, key));
        }
    }

    private static void checkOrCreateOutObject(DocumentContext context, String jsonPathOutString) {
        Object outJsonObject = readJsonObject(context, jsonPathOutString);
        if (null != outJsonObject) {
            // Object already exists
            return;
        }

        // Path does not exist: if parent exists then we create a new object under the parent
        String parentJsonPath = JsonEngine.getParentJsonPath(jsonPathOutString);
        Object parentJsonObject = readJsonObject(context, parentJsonPath);
        if (parentJsonObject == null) {
            throw new IllegalArgumentException(String.format("Parent path not found in payload: '%s'!", parentJsonPath));
        }
        outJsonObject = jsonPathConfig.jsonProvider().createMap();
        String elementKey = JsonEngine.getJsonElementKey(jsonPathOutString);
        context.put(parentJsonPath, elementKey, outJsonObject);
    }

    private static Object readJsonObject(DocumentContext context, String jsonPathString) {
        Object jsonElement = readJsonElement(context, jsonPathString);
        if (jsonElement == null) {
            return null;
        }
        if (!jsonEngine.isJsonObject(jsonElement)) {
            throw new IllegalArgumentException(String.format("JSON object expected at path: '%s'!", jsonPathString));
        }
        return jsonElement;
    }

    private static Object readJsonElement(DocumentContext context, String jsonPathString) {
        Object payloadJsonObject = context.json();
        JsonPath jsonPath = JsonPath.compile(jsonPathString);
        return jsonPath.read(payloadJsonObject, jsonPathConfig);
    }

    private static Object readAndDeleteJsonKey(DocumentContext context, String objectPath, Object object, String key) {
        if (null == key) {
            // Do nothing
            return null;
        }
        JsonProvider jsonProvider = jsonPathConfig.jsonProvider();
        Object value = jsonProvider.getMapValue(object, key);
        context.delete(objectPath + "." + key);
        return value;
    }

    private static String sanitizeJson(String json) {
        return json.replaceAll("\n", "")
                .replaceAll("\r", "")
                .replaceAll("\t", "");
    }
}
