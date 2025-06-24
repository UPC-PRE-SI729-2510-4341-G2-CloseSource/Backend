// event-context/src/main/java/com/matchevent/event_context/EventContextApplication.java
package com.matchevent.event_context;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing              // activa createdAt / updatedAt
public class EventContextApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventContextApplication.class, args);
	}
}
