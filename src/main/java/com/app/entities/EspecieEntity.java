package com.app.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "especie")
public class EspecieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_especie")
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_zona")
    private ZonaEntity zona;

    @OneToMany(mappedBy = "especie", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<AnimalEntity> animales;

    @Column(name = "estado_activo", columnDefinition = "TINYINT(1) NULL DEFAULT '1'")
    private Boolean estadoActivo;

    @CreatedDate()
    @Column(name = "fecha_creacion", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date fechaCreacion;

    @Column(name = "fecha_modificacion", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date fechaModificacion;

    @PrePersist()
    private void prePersist() {
        estadoActivo = true;
        fechaCreacion = new Date();
        fechaModificacion = new Date();
    }
}
