package com.jobby.g6.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Celular {

    @Column(nullable = false)
    private Long numero;

    @Column(nullable = false)
    private boolean whatsapp;
}