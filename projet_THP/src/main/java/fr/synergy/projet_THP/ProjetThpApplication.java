package fr.synergy.projet_THP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@SpringBootApplication//(exclude={SecurityAutoConfiguration.class})// permet exclure l'application securité
public class ProjetThpApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProjetThpApplication.class, args);
	}
}
