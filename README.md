# productos-api — README.md

**Autor:** Matias Aguilera  
**Legajo:** 50747

---

## Descripción
API REST para la gestión de `Producto` (trabajo práctico de APIs REST con Spring Boot). Implementa arquitectura en capas, validaciones con Bean Validation, persistencia con Spring Data JPA + H2, manejo centralizado de errores y documentación con Swagger/OpenAPI.

---

## Tecnologías
- Java 17+  
- Spring Boot 3.x  
- Spring Web, Spring Data JPA, Validation, H2 Database, Lombok, Spring Boot DevTools  
- Springdoc OpenAPI (swagger-ui)  
- Maven

---

## Estructura recomendada de paquetes
```
com.utn.productos
 ├─ model
 ├─ dto
 ├─ repository
 ├─ service
 ├─ controller
 └─ exception
```

---

## Requisitos previos
- JDK 17+
- Maven (o usar `./mvnw`)
- Git

---

## Clonar y ejecutar
```bash
git clone https://github.com/AgusAguilera/productos-api.git
cd productos-api
./mvnw spring-boot:run    # o mvn spring-boot:run
```

---

## Endpoints de la API

La API sigue los principios REST para la gestión de la entidad `Producto`.

| Método HTTP | Ruta | Descripción |
| :--- | :--- | :--- |
| `GET` | `/api/productos` | Lista todos los productos. |
| `POST` | `/api/productos` | Crea un nuevo producto. |
| `GET` | `/api/productos/{id}` | Obtiene un producto específico por su ID. |
| `PUT` | `/api/productos/{id}` | Actualiza un producto completo por su ID. |
| `PATCH` | `/api/productos/{id}/stock` | Actualiza únicamente el stock de un producto. |
| `DELETE` | `/api/productos/{id}` | Elimina un producto por su ID. |
| `GET` | `/api/productos/categoria/{categoria}` | Filtra y lista productos por categoría. |

---

## DTOs y validaciones (resumen)
- **ProductoDTO** (crear/actualizar)
  - `nombre` — `@NotNull`, `@NotBlank`, `@Size(min=3,max=100)`
  - `descripcion` — `@Size(max=500)`
  - `precio` — `@NotNull`, `@Min(0.01)`
  - `stock` — `@NotNull`, `@Min(0)`
  - `categoria` — `@NotNull` (Enum: `ELECTRONICA, ROPA, ALIMENTOS, HOGAR, DEPORTES`)
- **ProductoResponseDTO**
  - Todos los campos, incluye `id` (solo lectura)
- **ActualizarStockDTO**
  - `stock` — `@NotNull`, `@Min(0)`

Usar `@Valid` en controladores para activar validaciones.

---

## Manejo de excepciones 
- `ProductoNotFoundException` → 404
- `StockInsuficienteException` (opcional) → 400/409 según diseño
- `MethodArgumentNotValidException` → 400 con detalles de validación
- `Exception` → 500 (errores generales)

Implementar `@ControllerAdvice` con un `ErrorResponse` que contenga: `timestamp`, `status`, `message`, `path`.

---

## Ejemplos rápidos 

**Crear producto (POST)**  
```bash
curl -i -X POST http://localhost:8080/api/productos  -H "Content-Type: application/json"  -d '{"nombre":"Auriculares","descripcion":"Inalámbricos","precio":59.99,"stock":10,"categoria":"ELECTRONICA"}'
```

**Listar todos (GET)**  
```bash
curl http://localhost:8080/api/productos
```

**Obtener por id (GET)**  
```bash
curl http://localhost:8080/api/productos/1
```

**Actualizar completo (PUT)**  
```bash
curl -i -X PUT http://localhost:8080/api/productos/1  -H "Content-Type: application/json"  -d '{"nombre":"Auriculares Pro","descripcion":"Bluetooth","precio":79.99,"stock":8,"categoria":"ELECTRONICA"}'
```

**Actualizar stock (PATCH)**  
```bash
curl -i -X PATCH http://localhost:8080/api/productos/1/stock  -H "Content-Type: application/json"  -d '{"stock":5}'
```

**Eliminar (DELETE)**  
```bash
curl -i -X DELETE http://localhost:8080/api/productos/1
```

**Filtrar por categoría (GET)**  
```bash
curl http://localhost:8080/api/productos/categoria/ELECTRONICA
```
---

## Capturas / Fotos (qué tomar y nombres sugeridos)
Colocar las imágenes en `docs/images/` y mostrarlas en el README final.

1.  **Swagger UI** mostrando la documentación completa de endpoints.

<img width="1105" height="936" alt="image" src="https://github.com/user-attachments/assets/d661b7df-c3e0-4577-a6ac-ccfee8f9f762" />


2.  **POST exitoso** (respuesta 201 con cuerpo del producto creado).

<img width="899" height="883" alt="image" src="https://github.com/user-attachments/assets/f21174c5-c2bf-4bac-82e9-c6e685e5cdd4" />


3. `get_list.png` — **GET /api/productos** mostrando la lista con los 5 productos.


<img width="892" height="820" alt="image" src="https://github.com/user-attachments/assets/9386c6e0-f755-44ae-87a9-a649b776ed74" />



4. `error_404.png` — **Error 404** cuando se solicita un ID inexistente.

<img width="903" height="777" alt="image" src="https://github.com/user-attachments/assets/c7155b69-5b53-4a46-b363-573b7a025951" />


5. `error_400.png` — **Error 400** de validación (ej.: crear sin nombre o precio negativo).

<img width="895" height="907" alt="image" src="https://github.com/user-attachments/assets/b7fdbdd2-8404-4da4-a92c-6537e1dfd865" />


6. `h2_console.png` — **Consola H2** mostrando la tabla `producto` y registros.

<img width="707" height="335" alt="image" src="https://github.com/user-attachments/assets/fc77c980-93f3-49db-b2c1-37ecb5729f2e" />



---

## Commits recomendados (uno por cada parte)
- `feat: proyecto base (Spring Initializr + dependencias)`  
- `feat(model): enum Categoria y entidad Producto`  
- `feat(repository): ProductoRepository + método findByCategoria`  
- `feat(service): ProductoService métodos CRUD`  
- `feat(dto): ProductoDTO, ProductoResponseDTO, ActualizarStockDTO`  
- `feat(controller): ProductoController endpoints CRUD`  
- `feat(exception): excepciones y GlobalExceptionHandler`  
- `feat(swagger): documentación OpenAPI`  
- `test: pruebas y screenshots`  

---

## Entrega (qué incluir en el repo)
- Código completo y funcional.  
- `application.properties` configurado.  
- README.md (este).  
- Carpeta `docs/images` con las capturas solicitadas.  
- Commits significativos (una por cada parte completada).  
- Opcional: `data.sql` con 5 inserts iniciales para pruebas.

---

## Notas finales
- Verificar `spring.jpa.hibernate.ddl-auto` según persistencia deseada.  
- Confirmar que Swagger describa claramente DTOs y respuestas.  
- Si se desea, se pueden generar los `curl` para los 5 productos iniciales.
