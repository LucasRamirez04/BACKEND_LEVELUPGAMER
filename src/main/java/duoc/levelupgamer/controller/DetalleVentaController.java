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
import duoc.levelupgamer.models.entities.DetalleVentaModel;
import duoc.levelupgamer.service.DetalleVentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/detalle-ventas")
@Tag(name = "Detalle Venta", description = "Productos que se compraron en cada venta")
public class DetalleVentaController {

    @Autowired
    private DetalleVentaService detalleVentaService;

    @Operation(summary = "Listar todos los detalles de venta")
    @GetMapping("/listar")
    public ResponseEntity<List<DetalleVentaModel>> listarDetalles() {
        return ResponseEntity.ok(detalleVentaService.listarTodos());
    }

    @Operation(summary = "Buscar un detalle por su id")
    @GetMapping("/{id}")
    public ResponseEntity<DetalleVentaModel> buscarPorId(@PathVariable Integer id) {
        DetalleVentaModel detalle = detalleVentaService.buscarPorId(id);
        if (detalle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detalle);
    }

    @Operation(summary = "Buscar los detalles de una venta")
    @GetMapping("/venta/{idVenta}")
    public ResponseEntity<List<DetalleVentaModel>> buscarPorVenta(@PathVariable Integer idVenta) {
        List<DetalleVentaModel> detalles = detalleVentaService.buscarPorVenta(idVenta);
        if (detalles == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detalles);
    }

    @Operation(summary = "Registrar un nuevo detalle de venta")
    @PostMapping
    public ResponseEntity<DetalleVentaModel> guardar(@RequestBody DetalleVentaModel detalle) {
        DetalleVentaModel guardado = detalleVentaService.guardar(detalle);
        if (guardado == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @Operation(summary = "Actualizar la cantidad de un detalle")
    @PutMapping("/{id}")
    public ResponseEntity<DetalleVentaModel> actualizar(@PathVariable Integer id,
                                                        @RequestBody DetalleVentaModel detalle) {
        DetalleVentaModel actualizado = detalleVentaService.actualizar(id, detalle);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @Operation(summary = "Eliminar un detalle de venta")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (!detalleVentaService.eliminar(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}