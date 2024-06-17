package com.app.validations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExisteEnBdValidacion implements ConstraintValidator<ExisteEnBD, Object> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class<?> claseEntidad;
    private String campo;

    @Override
    public void initialize(ExisteEnBD constraintAnnotation) {
        this.claseEntidad = constraintAnnotation.entity();
        this.campo = constraintAnnotation.field();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        System.out.println("Vamos a validar que existe un elemento en la clase: " + claseEntidad.getSimpleName());
        System.out.println("Vamos a validar la clase por el campo: " + campo);

        // Crear la consulta dinámica para verificar la existencia del valor en el campo especificado
        String query = String.format("SELECT COUNT(e) FROM %s e WHERE e.%s = :value",
                claseEntidad.getSimpleName(), campo);
        Long count = entityManager.createQuery(query, Long.class)
                .setParameter("value", value)
                .getSingleResult();

        System.out.println("Cantidad de zonas encontradas: " + count);

        return count <= 0;
    }
}
