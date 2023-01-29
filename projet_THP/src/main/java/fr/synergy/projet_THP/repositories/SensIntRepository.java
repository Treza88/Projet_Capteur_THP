package fr.synergy.projet_THP.repositories;

import fr.synergy.projet_THP.entities.SensCaveEntity;
import fr.synergy.projet_THP.entities.SensIntEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensIntRepository extends CrudRepository<SensIntEntity,Long> {
    @Query(value ="SELECT * FROM sens_int ORDER BY id_int DESC LIMIT 24",
            nativeQuery = true)
    Iterable<SensIntEntity> findByIdInvOrderLast24();

}
