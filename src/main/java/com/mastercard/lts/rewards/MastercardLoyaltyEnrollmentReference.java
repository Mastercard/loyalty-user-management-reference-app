package com.mastercard.lts.rewards;


import com.mastercard.developer.mastercard_loyalty_enrollment_client.ApiClient;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.ApiException;
import com.mastercard.lts.rewards.config.EnrollmentConfig;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

public class MastercardLoyaltyEnrollmentReference {

    private static final ApiClient apiClient = new ApiClient();

    public static void main(String args[]) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException,
            NoSuchProviderException, KeyStoreException, ApiException {
        EnrollmentConfig.setupApiClient(apiClient);
    }
}
