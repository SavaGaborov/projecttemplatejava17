package template.java17;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "Open API", version = "3.0", description = "Swagger"))
@SpringBootApplication
public class Java17Application {

	public static void main(String[] args) {
		SpringApplication.run(Java17Application.class, args);
	}

}
