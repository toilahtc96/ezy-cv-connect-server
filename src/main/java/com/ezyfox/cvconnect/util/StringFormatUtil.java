package com.ezyfox.cvconnect.util;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;

import java.text.Normalizer;
import java.util.regex.Pattern;

@EzySingleton
public class StringFormatUtil {

    public String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern
            .matcher(temp)
            .replaceAll("")
            .replace('đ', 'd')
            .replace('Đ', 'D');
    }
}
