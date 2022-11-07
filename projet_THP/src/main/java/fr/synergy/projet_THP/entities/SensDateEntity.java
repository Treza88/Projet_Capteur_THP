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
@Table(name="SensDate")
public class SensDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_date")
    private Long idDate;
    @Column(nullable = false)
    private  Integer annee;
    @Column(nullable = false)
    private  Integer mois;
    @Column(nullable = false)
    private  Integer jour;
    @Column(nullable = false)
    private  Integer heure;
    @Column(nullable = false)
    private  Integer minute;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Calendar dateServer;
    @OneToOne(mappedBy = "idDate")
    private SensIntEntity sensInt;

}
