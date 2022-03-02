package template.java17.configuration.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import template.java17.configuration.CustomProperties;
import template.java17.web.rest.exception.RestAuthenticationEntryPoint;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static template.java17.configuration.security.WebSecurityConfiguration.UNSECURED_APIS;


@RequiredArgsConstructor
public class ApiKeyRequestFilter extends OncePerRequestFilter {

    private final PathMatcher pathMatcher;
    private final CustomProperties customProperties;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        AbstractAuthenticationToken abstractAuthenticationToken = null;
        try {
            final String apiKey = httpServletRequest.getHeader("X-Api-Key");
            if (apiKey != null && apiKey.equals("apikljuc")) {   //TODO api key from custom properties
                abstractAuthenticationToken = new ApiKeyAuthenticationToken(apiKey);
                abstractAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(abstractAuthenticationToken);
            }
        } catch (Exception ignored) {
        }

        if (abstractAuthenticationToken == null) {
            restAuthenticationEntryPoint.commence(httpServletRequest, httpServletResponse, null);
            return;
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return UNSECURED_APIS.stream()
                .anyMatch(api -> pathMatcher.match(api, request.getServletPath()));
    }
}
