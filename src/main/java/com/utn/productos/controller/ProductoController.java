package com.utn.productos.controller;

import com.utn.productos.dto.ActualizarStockDTO;
import com.utn.productos.dto.ProductoDTO;
import com.utn.productos.dto.ProductoResponseDTO;
import com.utn.productos.exception.ErrorResponse;
import com.utn.productos.model.Categoria;
import com.utn.productos.service.ProductoService;

// Importaciones de OpenAPI (Swagger)
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "Gestión de Productos", description = "Endpoints para crear, leer, actualizar y eliminar productos.")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @Operation(summary = "Listar todos los productos", description = "Obtiene una lista de todos los productos disponibles.")
    @ApiResponse(responseCode = "200", description = "Lista de productos obtenida con éxito.")
    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(productoService.obtenerTodos());
    }

    @Operation(summary = "Obtener producto por ID", description = "Busca y devuelve un producto específico por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado con éxito."),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.obtenerPorId(id));
    }

    @Operation(summary = "Filtrar productos por categoría", description = "Obtiene una lista de productos que pertenecen a una categoría específica.")
    @ApiResponse(responseCode = "200", description = "Lista de productos filtrada por categoría.")
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<ProductoResponseDTO>> obtenerPorCategoria(@PathVariable Categoria categoria) {
        return ResponseEntity.ok(productoService.obtenerPorCategoria(categoria));
    }

    @Operation(summary = "Crear un nuevo producto", description = "Agrega un nuevo producto al sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado exitosamente."),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos (error de validación).",
                    content = @Content(schema = @Schema(implementation = Map.class)))
    })
    @PostMapping
    public ResponseEntity<ProductoResponseDTO> crearProducto(@Valid @RequestBody ProductoDTO productoDTO) {
        ProductoResponseDTO productoGuardado = productoService.crearProducto(productoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoGuardado);
    }

    @Operation(summary = "Actualizar un producto completo", description = "Actualiza todos los campos de un producto existente usando su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente."),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos (error de validación).",
                    content = @Content(schema = @Schema(implementation = Map.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizarProducto(@PathVariable Long id, @Valid @RequestBody ProductoDTO productoDTO) {
        return ResponseEntity.ok(productoService.actualizarProducto(id, productoDTO));
    }

    @Operation(summary = "Actualizar solo el stock", description = "Actualiza únicamente el stock de un producto específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Stock actualizado exitosamente."),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos (stock negativo, etc.).",
                    content = @Content(schema = @Schema(implementation = Map.class)))
    })
    @PatchMapping("/{id}/stock")
    public ResponseEntity<ProductoResponseDTO> actualizarStock(@PathVariable Long id, @Valid @RequestBody ActualizarStockDTO stockDTO) {
        return ResponseEntity.ok(productoService.actualizarStock(id, stockDTO.getStock()));
    }

    @Operation(summary = "Eliminar un producto", description = "Elimina un producto del sistema por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Producto eliminado exitosamente."),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}