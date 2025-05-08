package com.codeid.be_eshopay.util;

import java.util.Base64;

public class ImageUtil {

    public static String convertToBase64(byte[] byteaData) {
        if (byteaData == null) {
            return null;
        }
        return Base64.getEncoder().encodeToString(byteaData);
    }
}
