package template.java17.web.rest.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

import static template.java17.util.HttpUtil.getLocaleFromHeader;


@AllArgsConstructor
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint, AccessDeniedHandler {

    private final String locationUrl;
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException {
        handleUnauthorized(httpServletRequest, httpServletResponse);
    }

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                       AccessDeniedException e) throws IOException {
        handleUnauthorized(httpServletRequest, httpServletResponse);
    }

    private void handleUnauthorized(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        handle(httpServletRequest, httpServletResponse, "access.denied", HttpStatus.UNAUTHORIZED);
    }

    public void handleRedirect(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        handle(httpServletRequest, httpServletResponse, "resource.moved", HttpStatus.MOVED_PERMANENTLY);
    }

    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                       String resourceBundleKey, HttpStatus httpStatus) throws IOException {
        ExceptionResponse exceptionResponse = new ExceptionResponse(resourceBundleKey,
                getLocaleFromHeader(httpServletRequest),
                httpStatus);
        httpServletResponse.setHeader("Content-Type", "application/json");
        httpServletResponse.setHeader("Location", locationUrl);
        httpServletResponse.setStatus(httpStatus.value());
        OutputStream out = httpServletResponse.getOutputStream();
        objectMapper.writeValue(out, exceptionResponse);
        out.flush();
    }
}
