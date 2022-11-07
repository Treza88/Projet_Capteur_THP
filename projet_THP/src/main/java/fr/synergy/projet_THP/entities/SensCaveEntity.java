package fr.synergy.projet_THP.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="SensCave")
public class SensCaveEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_cave")
    private Long idCave;
    @Column(nullable = false,name = "temp_cave")
    private float tempCave;
    @Column(nullable = false,name = "humid_cave")
    private float himidCave;
    @OneToOne
    @JoinColumn(nullable = false,name = "id_date")
    private SensDateEntity idDate;
    @ManyToOne
    @JoinColumn(nullable = false,name = "id_lieu")
    private SensLieuEntity idLieu;
}
