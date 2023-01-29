package fr.synergy.projet_THP.repositories;

import fr.synergy.projet_THP.entities.BackUserEntity;
import fr.synergy.projet_THP.entities.SensCaveEntity;
import fr.synergy.projet_THP.entities.UserRoleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BackUserRepository extends CrudRepository<BackUserEntity,Long> {

    @Query("SELECT u FROM BackUserEntity u WHERE u.username = :username")
    public BackUserEntity getUserByUsername(@Param("username") String username);




}
