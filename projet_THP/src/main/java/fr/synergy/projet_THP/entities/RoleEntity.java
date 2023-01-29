package fr.synergy.projet_THP.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="RoleEntity")
@Table(name = "roles_admin")
public class RoleEntity {

        public String getName() {
                return name;
        }

        @Id
        @Column(name = "role_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;



//        @ManyToMany(mappedBy = "roles")
//        private Set<BackUserEntity> backUserEntitys = new HashSet<>();
}
