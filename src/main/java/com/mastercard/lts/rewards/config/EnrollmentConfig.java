package com.mastercard.lts.rewards.config;


import com.mastercard.developer.interceptors.OkHttpOAuth1Interceptor;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.ApiClient;
import com.mastercard.developer.utils.AuthenticationUtils;
import com.mastercard.lts.rewards.MastercardLoyaltyEnrollmentReference;
import okhttp3.Interceptor;

import java.io.IOException;
import java.net.URL;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.List;

public class EnrollmentConfig {

    private static final String CONSUMER_KEY = "your_consumer_key";
    private static final String KEYSTORE_PASSWORD = "your_keystore_password";
    private static final String KEY_ALIAS = "your_key_alias";
    private static final String P12_KEY_FILE_NAME = "mastercard-loyalty-enrollment-sandbox.p12";

    public static void setupApiClient(ApiClient apiClient) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyStoreException, NoSuchProviderException {
        ClassLoader loader = MastercardLoyaltyEnrollmentReference.class.getClassLoader();
        URL url = loader.getResource(P12_KEY_FILE_NAME);
        List<Interceptor> interceptors = apiClient.getHttpClient().interceptors();
        apiClient.setBasePath("https://sandbox.api.mastercard.com/loyalty/enrollment/api");
        PrivateKey signingKey = AuthenticationUtils.loadSigningKey(url.getPath(), KEY_ALIAS, KEYSTORE_PASSWORD);
        interceptors.add(new OkHttpOAuth1Interceptor(CONSUMER_KEY, signingKey));
    }
}
