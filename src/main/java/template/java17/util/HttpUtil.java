package template.java17.util;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Locale;

public class HttpUtil {

    private HttpUtil() {

    }

    public static Locale getLocaleFromHeader(HttpServletRequest httpServletRequest) {
        final String languageCode = httpServletRequest.getHeader("Language");
        return LanguageUtil.getLocaleFromLanguageCode(languageCode);
    }
}
