package fr.synergy.projet_THP.repositories;

import fr.synergy.projet_THP.entities.SensCaveEntity;
import fr.synergy.projet_THP.entities.SensExtEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensExtRepository extends CrudRepository<SensExtEntity,Long> {
    @Query(value ="SELECT * FROM sens_ext ORDER BY id_ext DESC LIMIT 24",
            nativeQuery = true)
    Iterable<SensExtEntity> findByIdInvOrderLast24();

}
