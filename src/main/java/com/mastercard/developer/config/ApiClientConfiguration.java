package com.mastercard.developer.config;

import com.mastercard.developer.crypto.JweConfig;
import com.mastercard.developer.crypto.JweConfigBuilder;
import com.mastercard.developer.crypto.interceptors.OkHttpJweInterceptor;
import com.mastercard.developer.interceptors.OkHttpOAuth1Interceptor;
import com.mastercard.developer.utils.AuthenticationUtils;
import com.mastercard.developer.utils.EncryptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.client.ApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.PrivateKey;
import java.security.cert.Certificate;

/**
 * This is ApiClient configuration, it will read properties from application.properties and create instance of ApiClient.
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(MastercardProperties.class)
public class ApiClientConfiguration {

    @Bean
    public ApiClient apiClient(@Autowired MastercardProperties mcProperties) {
        ApiClient client = new ApiClient();
        try {
            Certificate certificate = EncryptionUtils.loadEncryptionCertificate(mcProperties.getEncryptionCertificateFile().getFile().getAbsolutePath());
            PrivateKey decryptionKey = EncryptionUtils.loadDecryptionKey(mcProperties.getDecryptionKeyFile().getFile().getAbsolutePath(), mcProperties.getDecryptionKeyAlias(), mcProperties.getDecryptionKeystorePassword());
            JweConfig jweConfig = JweConfigBuilder.builder()
                    .withEncryptionCertificate(certificate)
                    .withEncryptionKeyFingerprint(mcProperties.getEncryptionKeyFingerprint())
                    .withDecryptionKey(decryptionKey)
                    .build();

            PrivateKey signingKey = AuthenticationUtils.loadSigningKey(mcProperties.getKeyFile().getFile().getAbsolutePath(), mcProperties.getKeystoreAlias(), mcProperties.getKeystorePassword());
            client.setBasePath(mcProperties.getBasePath());
            client.setDebugging(true);
            client.setReadTimeout(40000);

            return client.setHttpClient(client.getHttpClient().newBuilder()
                    .addInterceptor(new OkHttpJweInterceptor(jweConfig))
                    .addInterceptor(new OkHttpOAuth1Interceptor(mcProperties.getConsumerKey(), signingKey))
                    .build()
            );
        } catch (Exception e) {
            log.error("Error occurred while configuring ApiClient", e);
        }
        return client;
    }
}
