package com.mastercard.developer.config;

import com.mastercard.developer.exception.ServiceException;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;

@Getter
@Setter
@ConfigurationProperties(prefix = "mastercard.api")
public class MastercardProperties {

    private String basePath;

    private String consumerKey;

    private String keystoreAlias;

    private String keystorePassword;

    private Resource keyFile;

    private Resource encryptionCertificateFile;

    private String encryptionKeyFingerprint;

    private Resource decryptionKeyFile;

    private String decryptionKeyAlias;

    private String decryptionKeystorePassword;

    @PostConstruct
    public void initialize() throws ServiceException {
        if (null == keyFile || StringUtils.isEmpty(consumerKey)) {
            throw new ServiceException(".p12 file or consumerKey does not exist, please add details in application.properties");
        }
        if (null == encryptionCertificateFile || null == decryptionKeyFile) {
            throw new ServiceException("Key parameters required for encryption/decryption are not set, please add details in application.properties");
        }
    }
}
