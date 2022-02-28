package template.java17.util;


import template.java17.domain.enumeration.LanguageCode;

import java.util.Locale;

public class LanguageUtil {

    private LanguageUtil() {

    }

    public static Locale getLocaleFromLanguageCode(LanguageCode languageCode) {
        Locale locale;
        try {
            locale = new Locale(languageCode.name());
        } catch (Exception ignored) {
            locale = Locale.GERMAN;
        }
        return locale;
    }

    public static Locale getLocaleFromLanguageCode(String languageCode) {
        Locale locale;
        try {
            locale = new Locale(languageCode);
        } catch (Exception ignored) {
            locale = Locale.GERMAN;
        }
        return locale;
    }
}
