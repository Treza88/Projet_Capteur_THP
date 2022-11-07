package fr.synergy.projet_THP.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="SensLieu")
public class SensLieuEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_lieu")
    private Long idLieu;
    @Column(nullable = false,length = 20)
    private String lieu;
    @Column(nullable = false,length = 20)
    private String capteur;
    @OneToMany(mappedBy = "idLieu")
    private Collection<SensIntEntity> sensInts;
}
