package template.java17.service.authentication.exception;

import template.java17.configuration.exception.ApplicationMainException;

public class EmailExistsException extends ApplicationMainException {

    public EmailExistsException() {
        super("email.exists");
    }
}
