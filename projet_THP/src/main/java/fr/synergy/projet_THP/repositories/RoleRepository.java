package fr.synergy.projet_THP.repositories;

import fr.synergy.projet_THP.entities.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
//@Component
@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {




    }

