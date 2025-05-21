package com.codeid.be_eshopay.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberUtil {
    public static double roundedDouble(double number) {
        return new BigDecimal(number).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
    
}
