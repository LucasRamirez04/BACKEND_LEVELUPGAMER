package duoc.levelupgamer.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import duoc.levelupgamer.models.entities.ProductoModel;
import duoc.levelupgamer.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/levelup/productos")
@Tag(name = "Productos", description = "Gestion del catalogo de productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Operation(summary = "Listar todos los productos")
    @GetMapping("/listar")
    public ResponseEntity<List<ProductoModel>> listarProductos() {
        return ResponseEntity.ok(productoService.listarTodos());
    }

    @Operation(summary = "Buscar un producto por su id")
    @GetMapping("/{id}")
    public ResponseEntity<ProductoModel> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(productoService.buscarPorId(id));
    }

    @Operation(summary = "Buscar productos por categoria")
    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<List<ProductoModel>> buscarPorCategoria(@PathVariable Integer idCategoria) {
        return ResponseEntity.ok(productoService.buscarPorCategoria(idCategoria));
    }

    @Operation(summary = "Registrar un nuevo producto")
    @PostMapping
    public ResponseEntity<ProductoModel> guardar(@Valid @RequestBody ProductoRequest request) {
        ProductoModel productoGuardado = productoService.crearDesdeRequest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoGuardado);
    }

    @Operation(summary = "Actualizar un producto")
    @PutMapping("/{id}")
    public ResponseEntity<ProductoModel> actualizar(@PathVariable Integer id, @Valid @RequestBody ProductoRequest request) {
        return ResponseEntity.ok(productoService.actualizar(id, request));
    }

    @Operation(summary = "Eliminar un producto")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}