package fr.synergy.projet_THP.repositories;

import fr.synergy.projet_THP.entities.BackUserEntity;
import fr.synergy.projet_THP.entities.SensCaveEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BackUserRepository extends CrudRepository<BackUserEntity,Long> {

    @Query(value ="SELECT login, password FROM user_admin",
            nativeQuery = true)
    Iterable<SensCaveEntity> findByLogin();



}
