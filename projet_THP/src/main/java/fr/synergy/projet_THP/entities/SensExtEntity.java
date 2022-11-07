package fr.synergy.projet_THP.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="SensExt")
public class SensExtEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_ext")
    private Long idExt;
    @Column(nullable = false,name = "temp_ext")
    private float tempExt;
    @Column(nullable = false,name = "humid_ext")
    private float himidExt;
    @Column(nullable = false,name = "press_ext")
    private float pressExt;

    @OneToOne
    @JoinColumn(nullable = false,name = "id_date")
    private SensDateEntity idDate;
    @ManyToOne
    @JoinColumn(nullable = false,name = "id_lieu")
    private SensLieuEntity idLieu;

}
