package template.java17.web.rest.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import template.java17.util.ResourceBundleUtil;

import java.util.List;
import java.util.Locale;

@NoArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class ExceptionResponse {

    @JsonProperty("status")
    private int status;

    @JsonProperty("status_text")
    private String statusText;

    @JsonProperty("localized_error_message")
    private String localizedErrorMessage;

    @JsonProperty("error_description")
    private String errorDescription;

    private List<String> data;

    public ExceptionResponse(String resourceBundleKey, Locale locale, HttpStatus status) {
        this();
        this.status = status.value();
        this.statusText = status.getReasonPhrase();
        this.localizedErrorMessage = translateErrorMessage(resourceBundleKey, locale);
        this.errorDescription = translateErrorMessage(resourceBundleKey, Locale.ENGLISH);
    }

    void setErrors(List<String> data) {
        this.data = data;
    }

    private String translateErrorMessage(String resourceBundleExceptionKey, Locale locale) {
        return ResourceBundleUtil.getExceptionValue(locale, resourceBundleExceptionKey);
    }
}
