package com.utn.productos.dto;

import com.utn.productos.model.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para crear o actualizar un producto")
public class ProductoDTO {

    @NotEmpty
    @Size(min = 3, max = 100)
    @Schema(description = "Nombre del producto", example = "Teclado Mecánico")
    private String nombre;

    @Size(max = 500)
    @Schema(description = "Descripción detallada del producto", example = "Teclado con switches Cherry MX Red")
    private String descripcion;

    @NotNull
    @DecimalMin("0.01")
    @Schema(description = "Precio unitario del producto", example = "89.99")
    private Double precio;

    @NotNull
    @Min(0)
    @Schema(description = "Cantidad de stock disponible", example = "50")
    private Integer stock;

    @NotNull
    @Schema(description = "Categoría a la que pertenece el producto", example = "ELECTRONICA")
    private Categoria categoria;
}