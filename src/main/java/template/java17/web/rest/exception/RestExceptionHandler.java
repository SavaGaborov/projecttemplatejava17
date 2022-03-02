package template.java17.web.rest.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import template.java17.configuration.exception.ApplicationMainException;
import template.java17.service.authentication.exception.CredentialsInvalidException;
import template.java17.service.authentication.exception.EmailExistsException;
import template.java17.service.user.exception.UserNotFoundException;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static template.java17.util.HttpUtil.getLocaleFromHeader;


@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {EmailExistsException.class})
    public ResponseEntity<ExceptionResponse> handleConflictException(HttpServletRequest httpServletRequest,
                                                                     ApplicationMainException e) {
        final Locale locale = getLocaleFromHeader(httpServletRequest);
        return buildResponseEntity(
                new ExceptionResponse(e.getResourceBundleKey(), locale, HttpStatus.CONFLICT));
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleNotFoundException(HttpServletRequest httpServletRequest,
                                                                     ApplicationMainException e) {
        final Locale locale = getLocaleFromHeader(httpServletRequest);
        return buildResponseEntity(
                new ExceptionResponse(e.getResourceBundleKey(), locale, HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(value = {CredentialsInvalidException.class})
    public ResponseEntity<ExceptionResponse> handleUnauthorizedException(HttpServletRequest httpServletRequest,
                                                                         ApplicationMainException e) {
        log.error("Handling access denied error - {}", e.getMessage());
        final Locale locale = getLocaleFromHeader(httpServletRequest);
        return buildResponseEntity(
                new ExceptionResponse(e.getResourceBundleKey(), locale, HttpStatus.UNAUTHORIZED));
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<ExceptionResponse> handleAccessDeniedException(HttpServletRequest httpServletRequest,
                                                                         Exception e) {
        log.error("Handling access denied error - {} for {}", e.getMessage(), httpServletRequest.getServletPath());
        final Locale locale = getLocaleFromHeader(httpServletRequest);
        return buildResponseEntity(new ExceptionResponse("access.denied", locale, HttpStatus.FORBIDDEN));
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ExceptionResponse> handleException(HttpServletRequest httpServletRequest, Exception e) {
        log.error("Handling unknown error - {}", e.getMessage(), e);
        final Locale locale = getLocaleFromHeader(httpServletRequest);
        return buildResponseEntity(
                new ExceptionResponse("internal.server.error", locale, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        log.error("Handling method argument not valid error - {}", ex.getMessage());
        ExceptionResponse response = new ExceptionResponse("invalid.input", Locale.GERMAN, HttpStatus.BAD_REQUEST);
        response.setErrors(fromBindingErrors(ex.getBindingResult()));
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    private ResponseEntity<ExceptionResponse> buildResponseEntity(ExceptionResponse exceptionResponse) {
        log.error("Handling error - {}", exceptionResponse);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.valueOf(exceptionResponse.getStatus()));
    }

    private List<String> fromBindingErrors(Errors errors) {
        List<String> validErrors = new ArrayList<>();
        for (ObjectError objectError : errors.getAllErrors()) {
            validErrors.add(((FieldError) objectError).getField() + " " + objectError.getDefaultMessage());
        }
        return validErrors;
    }
}
