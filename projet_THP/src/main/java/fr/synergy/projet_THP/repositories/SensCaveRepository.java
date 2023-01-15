package fr.synergy.projet_THP.repositories;

import fr.synergy.projet_THP.entities.SensCaveEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensCaveRepository extends CrudRepository<SensCaveEntity, Long> {

    @Query(value ="SELECT * FROM sens_cave ORDER BY id_cave DESC LIMIT 24",
            nativeQuery = true)
    Iterable<SensCaveEntity> findByIdInvOrderLast24();



}
