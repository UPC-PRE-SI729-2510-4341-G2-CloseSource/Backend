package pe.edu.upc.matchevent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MatcheventApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatcheventApplication.class, args);
	}

}
