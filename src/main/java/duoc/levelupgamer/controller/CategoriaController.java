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
import duoc.levelupgamer.models.entities.CategoriaModel;
import duoc.levelupgamer.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/categorias")
@Tag(name = "Categorias", description = "Gestion de categorias de productos")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Operation(summary = "Listar todas las categorias")
    @GetMapping("/listar")
    public ResponseEntity<List<CategoriaModel>> listarCategorias() {
        return ResponseEntity.ok(categoriaService.listarTodos());
    }

    @Operation(summary = "Buscar una categoria por su id")
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaModel> buscarPorId(@PathVariable Integer id) {
        CategoriaModel categoria = categoriaService.buscarPorId(id);
        if (categoria == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoria);
    }

    @Operation(summary = "Registrar una nueva categoria")
    @PostMapping
    public ResponseEntity<CategoriaModel> guardar(@RequestBody CategoriaModel categoria) {
        CategoriaModel categoriaGuardada = categoriaService.guardar(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaGuardada);
    }

    @Operation(summary = "Actualizar una categoria")
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaModel> actualizar(@PathVariable Integer id,
                                                     @RequestBody CategoriaModel categoria) {
        CategoriaModel actualizada = categoriaService.actualizar(id, categoria);
        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizada);
    }

    @Operation(summary = "Eliminar una categoria")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (!categoriaService.eliminar(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}