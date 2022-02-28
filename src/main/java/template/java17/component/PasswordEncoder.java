package template.java17.component;

public interface PasswordEncoder {

    String generateSalt();

    String encode(String rawPassword);

    boolean matches(String rawPassword, String encodedPassword);
}
