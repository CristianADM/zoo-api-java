package com.app.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String correo;

    @Column(length = 250, nullable = false)
    private String contrasenna;

    @Column(length = 260)
    private String token;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "usuarios_roles",
                joinColumns = @JoinColumn(name = "id_usuario"),
                inverseJoinColumns = @JoinColumn(name = "id_rol"))
    private Set<RolesEntity> roles = new HashSet<>();
}
