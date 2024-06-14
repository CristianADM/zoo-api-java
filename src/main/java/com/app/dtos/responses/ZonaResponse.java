package com.app.dtos.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
public class ZonaResponse {

    private Long id;
    private String nombre;
}
