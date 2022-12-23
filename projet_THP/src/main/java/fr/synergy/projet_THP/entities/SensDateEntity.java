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
    @Column(nullable = false,columnDefinition = "INt(4)")
    private  Integer annee;
    @Column(nullable = false,columnDefinition = "INT(2)")
    private  Integer mois;
    @Column(nullable = false,columnDefinition = "INT(2)")
    private  Integer jour;
    @Column(nullable = false,columnDefinition = "INT(2)")
    private  Integer heure;
    @Column(nullable = false,columnDefinition = "INT(2)")
    private  Integer minute;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false,columnDefinition = "TIMESTAMP")
    private Calendar dateServer;
    @OneToOne(mappedBy = "idDate")
    private SensIntEntity sensInt;
    @OneToOne(mappedBy = "idDate")
    private SensExtEntity sensExt;
    @OneToOne(mappedBy = "idDate")
    private SensCaveEntity sensCave;
}
