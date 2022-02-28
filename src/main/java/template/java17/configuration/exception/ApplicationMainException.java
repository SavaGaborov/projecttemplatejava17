package template.java17.configuration.exception;

import lombok.Getter;

@Getter
public class ApplicationMainException extends RuntimeException {

    private final String resourceBundleKey;

    public ApplicationMainException(final String resourceBundleKey) {
        this.resourceBundleKey = resourceBundleKey;
    }
}