package com.mastercard.developer.util;

import org.openapitools.client.JSON;

public class GsonProvider {

    private static final JSON INSTANCE = new JSON();

    private GsonProvider() {
    }

    public static JSON gson() {
        return INSTANCE;
    }
}
