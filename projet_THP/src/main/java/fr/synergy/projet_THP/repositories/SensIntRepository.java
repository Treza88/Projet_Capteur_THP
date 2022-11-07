package fr.synergy.projet_THP.repositories;

import fr.synergy.projet_THP.entities.SensIntEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensIntRepository extends CrudRepository<SensIntEntity,Long> {

}
