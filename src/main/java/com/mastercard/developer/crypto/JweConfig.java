package com.mastercard.developer.crypto;

import java.security.PrivateKey;
import java.security.cert.Certificate;

/**
 * A POJO for storing the encryption/decryption configuration.
 */
public final class JweConfig {

    /**
     * A certificate object whose public key will be used for encryption.
     */
    protected Certificate encryptionCertificate;

    /**
     * The SHA-256 hex-encoded digest of the key used for encryption (optional, the digest will be
     * automatically computed if this field is null or empty).
     * Example: "a3af46dc5b79726e5aa932f8dbdec616425350fd82a1c33f053e095b60f5cef3"
     */
    protected String encryptionKeyFingerprint;

    /**
     * A private key object to be used for decryption.
     */
    protected PrivateKey decryptionKey;

    protected JweConfig() {
    }

    public Certificate getEncryptionCertificate() {
        return encryptionCertificate;
    }

    public String getEncryptionKeyFingerprint() {
        return encryptionKeyFingerprint;
    }

    public PrivateKey getDecryptionKey() {
        return decryptionKey;
    }
}
