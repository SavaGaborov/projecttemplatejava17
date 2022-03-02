package template.java17.configuration.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import template.java17.component.TokenEncoder;
import template.java17.domain.User;
import template.java17.repository.UserRepository;
import template.java17.web.rest.exception.RestAuthenticationEntryPoint;

import java.io.IOException;
import java.time.ZoneOffset;
import java.util.Optional;

import static template.java17.configuration.security.WebSecurityConfiguration.UNSECURED_APIS;

@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    private final PathMatcher pathMatcher;
    private final TokenEncoder tokenEncoder;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = null;
        try {
            final String authorizationHeader = httpServletRequest.getHeader("Authorization");
            if (authorizationHeader != null) {
                final String jwt;
                jwt = tokenEncoder.getBearerTokenFromHeader(authorizationHeader);
                if (!tokenEncoder.isExpired(jwt)) {
                    final Claims claims = tokenEncoder.getClaims(jwt);
                    final Optional<User> user = userRepository.findUserByEmail(claims.getSubject());
                    if (user.isPresent()) {
                        usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                                user.get(), user.get(), user.get().getAuthorities());
                        usernamePasswordAuthenticationToken
                                .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }
                }
            }
        } catch (Exception ignored) {
        }

        if (usernamePasswordAuthenticationToken == null) {
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
