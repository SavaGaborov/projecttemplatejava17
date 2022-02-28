package template.java17.service.user.exception;

import template.java17.configuration.exception.ApplicationMainException;

public class UserNotFoundException extends ApplicationMainException {

    public UserNotFoundException() {
        super("user.not.found");
    }
}
