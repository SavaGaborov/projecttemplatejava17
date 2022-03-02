package template.java17.util;

import java.util.Locale;
import java.util.ResourceBundle;

public final class ResourceBundleUtil {

    private ResourceBundleUtil() {
    }

    private static final String RESOURCE_BUNDLE_CONTENT = "i18n.content";
    private static final String RESOURCE_BUNDLE_EXCEPTION = "i18n.exception";

    private final static ResourceBundle.Control resourceControl = new UTF8ResourceBundleControl();

    public static String getContentValue(Locale locale, String resourceBundleKey) {
        return getResource(locale, RESOURCE_BUNDLE_CONTENT, resourceBundleKey);
    }

    public static String getExceptionValue(Locale locale, String resourceBundleKey) {
        return getResource(locale, RESOURCE_BUNDLE_EXCEPTION, resourceBundleKey);
    }

    private static String getResource(Locale locale, String resourceBundleName, String resourceBundleKey) {
        return ResourceBundle.getBundle(resourceBundleName, locale, resourceControl).getString(resourceBundleKey);
    }
}
