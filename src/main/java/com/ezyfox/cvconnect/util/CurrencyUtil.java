package com.ezyfox.cvconnect.util;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyUtil {

    private static final Locale localeVN = new Locale("vi", "VN");
    private static final Locale localeEN = new Locale("en", "en");
    private static final NumberFormat vn = NumberFormat.getInstance(localeVN);
    private static final   NumberFormat en = NumberFormat.getInstance(localeEN);

    public static String vnFormat(double currency) {
        return vn.format(currency);
    }

    public static String enFormat(double currency) {
        return en.format(currency);
    }
}
