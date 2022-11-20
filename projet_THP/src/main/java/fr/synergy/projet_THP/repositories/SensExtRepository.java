package fr.synergy.projet_THP.repositories;

import fr.synergy.projet_THP.entities.SensExtEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensExtRepository extends CrudRepository<SensExtEntity,Long> {


}
