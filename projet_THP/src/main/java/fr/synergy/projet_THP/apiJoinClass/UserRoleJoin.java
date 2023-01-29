package fr.synergy.projet_THP.apiJoinClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.type.LongType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.security.PrivateKey;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class UserRoleJoin {

private BigInteger id;
private String name;
    private String username;
    private String password;
    private Boolean enabled;


    @Autowired
    EntityManager em;

    public Iterable<UserRoleJoin> findUserRole(){
        String req ="SELECT ur.user_id,r.name,username,password,enabled FROM user_admin u JOIN users_roles ur on u.user_id=ur.user_id JOIN roles_admin r on ur.role_id=r.role_id";

        Query query = em.createNativeQuery(req);
        List<Object[]> all = query.getResultList();
        List<UserRoleJoin> userRoleJoins = new ArrayList<>();

        for (Object[] o : all) {
            UserRoleJoin t = new UserRoleJoin();
            t.setId((BigInteger) o[0]);
            t.setName((String) o[1]);
            t.setUsername((String) o[2]);
            t.setPassword((String) o[3]);
            t.setEnabled((Boolean) o[4]);
            userRoleJoins.add(t);
            System.out.println(t);
        }
        System.out.println(userRoleJoins);

        return userRoleJoins;

    }
}
