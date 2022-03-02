package template.java17.component;

import io.jsonwebtoken.Claims;
import template.java17.domain.User;

public interface TokenEncoder{

    Claims getClaims(String jwt);

    String generate(User user);

    boolean isExpired(String jwt);

    default String getBearerTokenFromHeader(String authorizationHeader) {
        String accessToken = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            accessToken = authorizationHeader.substring(7);
        }
        return accessToken;
    }
}
