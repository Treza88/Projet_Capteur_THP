package fr.synergy.projet_THP.repositories;

import fr.synergy.projet_THP.entities.UserRoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRoleEntity,Long>{


}
