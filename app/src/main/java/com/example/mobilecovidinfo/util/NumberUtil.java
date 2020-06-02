package com.example.mobilecovidinfo.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class NumberUtil {
    public static String currencyFormat(String amount) {
        NumberFormat formatter = DecimalFormat.getNumberInstance(new Locale("pt", "BR"));
        return formatter.format(Double.parseDouble(amount));
    }
}
