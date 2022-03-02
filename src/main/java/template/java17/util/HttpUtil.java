package template.java17.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public final class HttpUtil {

    private HttpUtil() {

    }

    public static Locale getLocaleFromHeader(HttpServletRequest httpServletRequest) {
        final String languageCode = httpServletRequest.getHeader("Language");
        return LanguageUtil.getLocaleFromLanguageCode(languageCode);
    }
}
