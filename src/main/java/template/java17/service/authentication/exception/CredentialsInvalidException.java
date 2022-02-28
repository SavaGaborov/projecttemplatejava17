package template.java17.service.authentication.exception;

import template.java17.configuration.exception.ApplicationMainException;

public class CredentialsInvalidException extends ApplicationMainException {

    public CredentialsInvalidException() {
        super("credentials.invalid");
    }
}

