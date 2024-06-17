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
@Table(name = "comentario")
public class ComentarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comentario")
    private Long id;

    @Column(nullable = false, length = 500)
    private String cuerpo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private UserEntity usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_animal")
    private AnimalEntity animal;

    @ManyToOne
    @JoinColumn(name = "id_comentario_padre")
    private ComentarioEntity comentarioPadre;

    @OneToMany(mappedBy = "comentarioPadre", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComentarioEntity> subComentarios;

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
