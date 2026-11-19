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
import duoc.levelupgamer.models.entities.VentaModel;
import duoc.levelupgamer.service.VentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/ventas")
@Tag(name = "Ventas", description = "Gestion de ventas realizadas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @Operation(summary = "Listar todas las ventas")
    @GetMapping("/listar")
    public ResponseEntity<List<VentaModel>> listarVentas() {
        return ResponseEntity.ok(ventaService.listarTodos());
    }

    @Operation(summary = "Buscar una venta por su id")
    @GetMapping("/{id}")
    public ResponseEntity<VentaModel> buscarPorId(@PathVariable Integer id) {
        VentaModel venta = ventaService.buscarPorId(id);
        if (venta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(venta);
    }

    @Operation(summary = "Registrar una nueva venta")
    @PostMapping
    public ResponseEntity<VentaModel> guardar(@RequestBody VentaModel venta) {
        VentaModel ventaGuardada = ventaService.guardar(venta);
        if (ventaGuardada == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(ventaGuardada);
    }

    @Operation(summary = "Actualizar una venta")
    @PutMapping("/{id}")
    public ResponseEntity<VentaModel> actualizar(@PathVariable Integer id,
                                                 @RequestBody VentaModel venta) {
        VentaModel actualizada = ventaService.actualizar(id, venta);
        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizada);
    }

    @Operation(summary = "Eliminar una venta")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (!ventaService.eliminar(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}