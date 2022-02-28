package template.java17.component.impl;

import template.java17.component.TokenEncoder;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.UUID;

@Component
@AllArgsConstructor
public class JwtTokenEncoder {

//    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
//    private static final String ISSUER = "checkback";
//
//    private final CustomProperties customProperties;
//
//    @Override
//    public Claims getClaims(String jwt) {
//        return Jwts.parserBuilder()
//                .setSigningKey("customProperties.getJwtSigningKey()").build()
//                .parseClaimsJws(jwt)
//                .getBody();
//    }
//
//    @Override
//    public String generate(User user) {
//        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("customProperties.getJwtSigningKey()");
//        final Key signingKey = new SecretKeySpec(apiKeySecretBytes, SIGNATURE_ALGORITHM.getJcaName());
//
//        final long currentTimeMillis = System.currentTimeMillis();
//        return Jwts.builder()
//                .setId(UUID.randomUUID().toString())
//                .claim("role", "user.getRole().name()")
//                .setSubject(String.valueOf("user.getEmail()"))
//                .setIssuer(ISSUER)
//                .setAudience("customProperties.getJwtAudience()")
//                .setIssuedAt(new Date(currentTimeMillis))
//                .setExpiration(
//                        new Date(Duration.ofDays(1l).plusMillis(currentTimeMillis).toMillis()))
//                .signWith(signingKey)
//                .compact();
//    }
//
//    @Override
//    public boolean isExpired(String jwt) {
//        final Claims claims = Jwts.parserBuilder()
//                .setSigningKey("customProperties.getJwtSigningKey()").build()
//                .parseClaimsJws(jwt).getBody();
//        return claims.getExpiration().before(new Date());
//    }
}
