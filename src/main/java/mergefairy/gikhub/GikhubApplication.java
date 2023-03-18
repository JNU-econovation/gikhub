package mergefairy.gikhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GikhubApplication {
	public static void main(String[] args) {
		SpringApplication.run(GikhubApplication.class, args);
	}
}
