package fr.synergy.projet_THP.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="SensInt")
public class SensIntEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_int")
    private Long idInt;
    @Column(nullable = false,name = "temp_int")
    private float tempInt;
    @Column(nullable = false,name = "humid_int")
    private float himidInt;
    @OneToOne
    @JoinColumn(nullable = false,name = "id_date")
    private SensDateEntity idDate;
    @ManyToOne
    @JoinColumn(nullable = false,name = "id_lieu")
    private SensLieuEntity idLieu;
}