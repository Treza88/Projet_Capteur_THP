package fr.synergy.projet_THP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})// permet exclure l'application securité
public class ProjetThpApplication {

	public static void main(String[] args) {

		SpringApplication.run(ProjetThpApplication.class, args);

	}

}
