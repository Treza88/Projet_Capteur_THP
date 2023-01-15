package fr.synergy.projet_THP.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="userAdmin")
public class BackUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_user_admin")
    private Long idUserAdmin;

    @Column(name="first_name",nullable = false)
    private String firstName;

    @Column(name="last_name",nullable = false)
    private String lastName;

    @Column(name="login",nullable = false)
private String login;

@Column(nullable = false)
private String password;

@Column(nullable = false)
private String role;

@Column(nullable = false)
private String mail;

@Temporal(TemporalType.TIMESTAMP)
@Column(nullable = false,columnDefinition = "TIMESTAMP")
private Calendar date;

    public BackUserEntity(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
