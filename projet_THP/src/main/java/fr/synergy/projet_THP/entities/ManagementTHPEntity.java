package fr.synergy.projet_THP.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="ManagementTHP")
public class ManagementTHPEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_thp")
    private Long idTHP;

    @OneToMany
    @Column(nullable = false, name="id_ext")
    private  Long idExt;
    @OneToMany
    @Column(nullable = false,name="id_int")
    private Long idInt;
    @OneToMany
    @Column(nullable = false,name="id_cave")
    private Long icCave;
}
