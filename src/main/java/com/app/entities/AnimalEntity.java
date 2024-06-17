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
@Table(name = "animal")
public class AnimalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_animal")
    private Long id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_espcie")
    private EspecieEntity especie;

    @OneToMany(mappedBy = "animal", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<ComentarioEntity> comentarios;

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
