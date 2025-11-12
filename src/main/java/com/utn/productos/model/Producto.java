package com.utn.productos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // [cite: 69]

    private String nombre; // [cite: 69]
    private String descripcion; // [cite: 70]
    private Double precio; // [cite: 71]
    private Integer stock; // [cite: 72]

    @Enumerated(EnumType.STRING)
    private Categoria categoria; //
}