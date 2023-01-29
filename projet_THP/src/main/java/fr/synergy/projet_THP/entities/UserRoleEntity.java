package fr.synergy.projet_THP.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="UserRoleEntity")
@Table(name="users_roles")
public class UserRoleEntity {
    @Id
    @Column(name="user_id")
    private Long id;

    @Column(name="role_id")
    private Long role;
}
