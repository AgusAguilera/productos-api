package com.utn.productos.dto;

import com.utn.productos.model.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para respuestas de producto (incluye ID)")
public class ProductoResponseDTO {

    @Schema(description = "ID único del producto", example = "1")
    private Long id;

    @Schema(description = "Nombre del producto", example = "Teclado Mecánico")
    private String nombre;

    @Schema(description = "Descripción detallada del producto", example = "Teclado con switches Cherry MX Red")
    private String descripcion;

    @Schema(description = "Precio unitario del producto", example = "89.99")
    private Double precio;

    @Schema(description = "Cantidad de stock disponible", example = "50")
    private Integer stock;

    @Schema(description = "Categoría a la que pertenece el producto", example = "ELECTRONICA")
    private Categoria categoria;
}