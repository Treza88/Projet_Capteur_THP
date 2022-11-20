package fr.synergy.projet_THP.repositories;

import fr.synergy.projet_THP.entities.SensCaveEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SenCaveRepository extends CrudRepository<SensCaveEntity, Long> {

}
