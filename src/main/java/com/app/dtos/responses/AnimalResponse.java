package com.app.dtos.responses;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnimalResponse {

    private Long id;
    private String nombre;
    private EspecieResponse especie;
}
