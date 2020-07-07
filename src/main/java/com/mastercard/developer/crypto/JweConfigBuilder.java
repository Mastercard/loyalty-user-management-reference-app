package com.mastercard.developer.crypto;

import com.mastercard.developer.encryption.EncryptionException;
import com.mastercard.developer.encryption.FieldLevelEncryptionConfig;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.Certificate;

import static com.mastercard.developer.utils.EncodingUtils.encodeBytes;
import static com.mastercard.developer.utils.StringUtils.isNullOrEmpty;

/**
 * A builder class for {@link com.mastercard.developer.crypto.JweConfig}.
 */
public class JweConfigBuilder {

    private Certificate encryptionCertificate;
    private String encryptionKeyFingerprint;
    private PrivateKey decryptionKey;

    private JweConfigBuilder() {
    }

    /**
     * Get an instance of the builder.
     */
    public static JweConfigBuilder builder() {
        return new JweConfigBuilder();
    }

    /**
     * See: {@link com.mastercard.developer.crypto.JweConfig#encryptionCertificate}.
     */
    public JweConfigBuilder withEncryptionCertificate(Certificate encryptionCertificate) {
        this.encryptionCertificate = encryptionCertificate;
        return this;
    }

    /**
     * See: {@link com.mastercard.developer.crypto.JweConfig#encryptionKeyFingerprint}.
     */
    public JweConfigBuilder withEncryptionKeyFingerprint(String encryptionKeyFingerprint) {
        this.encryptionKeyFingerprint = encryptionKeyFingerprint;
        return this;
    }

    /**
     * See: {@link com.mastercard.developer.crypto.JweConfig#decryptionKey}.
     */
    public JweConfigBuilder withDecryptionKey(PrivateKey decryptionKey) {
        this.decryptionKey = decryptionKey;
        return this;
    }

    /**
     * Build a {@link com.mastercard.developer.crypto.JweConfig}.
     *
     * @throws EncryptionException If error occurred while configuring JWE parameters.
     */
    public JweConfig build() throws EncryptionException {
        computeEncryptionKeyFingerprintWhenNeeded();

        JweConfig jweConfig = new JweConfig();
        jweConfig.encryptionCertificate = this.encryptionCertificate;
        jweConfig.encryptionKeyFingerprint = this.encryptionKeyFingerprint;
        jweConfig.decryptionKey = this.decryptionKey;
        return jweConfig;
    }

    private void computeEncryptionKeyFingerprintWhenNeeded() throws EncryptionException {
        try {
            if (encryptionCertificate == null || !isNullOrEmpty(encryptionKeyFingerprint)) {
                // No encryption certificate set or key fingerprint already provided
                return;
            }
            byte[] keyFingerprintBytes = sha256digestBytes(encryptionCertificate.getPublicKey().getEncoded());
            encryptionKeyFingerprint = encodeBytes(keyFingerprintBytes, FieldLevelEncryptionConfig.FieldValueEncoding.HEX);
        } catch (Exception e) {
            throw new EncryptionException("Failed to compute encryption key fingerprint!", e);
        }
    }

    private static byte[] sha256digestBytes(byte[] bytes) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(bytes);
        return messageDigest.digest();
    }
}
