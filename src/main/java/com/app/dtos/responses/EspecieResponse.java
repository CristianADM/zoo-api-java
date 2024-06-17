package com.app.dtos.responses;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EspecieResponse {

    private Long id;
    private String nombre;
    private ZonaResponse zona;
}
