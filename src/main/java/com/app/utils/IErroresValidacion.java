package com.app.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface IErroresValidacion {
    public ResponseEntity<?> validation(BindingResult result);
}
