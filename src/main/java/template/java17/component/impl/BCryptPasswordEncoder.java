package template.java17.component.impl;

import template.java17.component.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import template.java17.component.PasswordEncoder;

@Component
@RequiredArgsConstructor
public class BCryptPasswordEncoder implements PasswordEncoder {

//    private final CustomProperties customProperties;

    @Override
    public String generateSalt() {
        return BCrypt.gensalt();
    }

    @Override
    public String encode(String rawPassword) {
        return BCrypt.hashpw(rawPassword, "salt");
    } //TODO izvuci salt iz custom properties

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return BCrypt.hashpw(rawPassword, "salt").equals(encodedPassword); //TODO izvuci salt iz custom properties

}
}