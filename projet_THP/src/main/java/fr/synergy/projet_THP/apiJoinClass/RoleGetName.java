package fr.synergy.projet_THP.apiJoinClass;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
@Component

public class RoleGetName {

    @Autowired
    EntityManager em;

    public List<String[]> findRoleById(Long id){
String req = String.format(" SELECT r.name  FROM roles_admin r Join users_roles ur ON r.role_id=ur.role_id where ur.user_id = \"%d\"", id);

    Query query = em.createNativeQuery(req);

    List<String[]> all = query.getResultList();

        return all;
}
}