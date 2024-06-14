package com.app.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "zona")
public class ZonaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zona")
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String nombre;

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
