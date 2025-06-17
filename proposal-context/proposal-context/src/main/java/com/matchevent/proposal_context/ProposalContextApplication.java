package com.matchevent.proposal_context;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class ProposalContextApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProposalContextApplication.class, args);
	}
}
