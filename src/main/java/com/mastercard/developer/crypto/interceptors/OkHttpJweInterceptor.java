package com.mastercard.developer.crypto.interceptors;

import com.mastercard.developer.crypto.JweConfig;
import com.mastercard.developer.crypto.JweExecutor;
import com.mastercard.developer.encryption.EncryptionException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

import java.io.IOException;

import static com.mastercard.developer.utils.StringUtils.isNullOrEmpty;

/**
 * An OkHttp3 interceptor for encrypting/decrypting parts of HTTP payloads.
 * See: https://github.com/square/okhttp/wiki/Interceptors
 */
public class OkHttpJweInterceptor implements Interceptor {

    private final JweConfig jweConfig;

    public OkHttpJweInterceptor(JweConfig jweConfig) {
        this.jweConfig = jweConfig;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request encryptedRequest = handleRequest(chain.request(), jweConfig);
        Response encryptedResponse = chain.proceed(encryptedRequest);
        return handleResponse(encryptedResponse, jweConfig);
    }

    private static Request handleRequest(Request request, JweConfig jweConfig) throws IOException {
        try {
            // Check request actually has a payload
            RequestBody requestBody = request.body();
            if (null == requestBody || requestBody.contentLength() == 0) {
                // Nothing to encrypt
                return request;
            }

            // Read request payload
            String requestPayload;
            try (Buffer buffer = new Buffer()) {
                requestBody.writeTo(buffer);
                requestPayload = buffer.readUtf8();
            }

            // Encrypt fields
            String encryptedPayload = JweExecutor.encryptPayload(requestPayload, jweConfig);
            RequestBody encryptedBody = RequestBody.create(requestBody.contentType(), encryptedPayload);

            Request.Builder requestBuilder = request.newBuilder();
            return requestBuilder
                    .method(request.method(), encryptedBody)
                    .header("Content-Length", String.valueOf(encryptedBody.contentLength()))
                    .build();
        } catch (EncryptionException ex) {
            throw new IOException("Failed to intercept and encrypt request!", ex);
        }
    }

    private static Response handleResponse(Response response, JweConfig jweConfig) throws IOException {
        try {
            // Check response actually has a payload
            ResponseBody responseBody = response.body();
            if (null == responseBody) {
                // Nothing to decrypt
                return response;
            }

            // Read response payload
            String responsePayload = responseBody.string();
            if (isNullOrEmpty(responsePayload)) {
                // Nothing to decrypt
                return response;
            }

            // Decrypt fields
            String decryptedPayload = JweExecutor.decryptPayload(responsePayload, jweConfig);
            Response.Builder responseBuilder = response.newBuilder();
            try (ResponseBody decryptedBody = ResponseBody.create(responseBody.contentType(), decryptedPayload)) {
                return responseBuilder
                        .body(decryptedBody)
                        .header("Content-Length", String.valueOf(decryptedBody.contentLength()))
                        .build();
            }
        } catch (EncryptionException ex) {
            throw new IOException("Failed to intercept and decrypt request!", ex);
        }
    }
}
