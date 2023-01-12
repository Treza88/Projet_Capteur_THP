package fr.synergy.projet_THP;

import fr.synergy.projet_THP.apiJoinClass.ApiTempJoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})// permet exclure l'application securité
public class ProjetThpApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetThpApplication.class, args);

	}




}
