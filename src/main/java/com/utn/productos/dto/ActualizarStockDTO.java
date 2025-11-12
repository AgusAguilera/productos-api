package com.utn.productos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para actualizar el stock de un producto")
public class ActualizarStockDTO {

    @NotNull
    @Min(0)
    @Schema(description = "La nueva cantidad total de stock", example = "75")
    private Integer stock;
}