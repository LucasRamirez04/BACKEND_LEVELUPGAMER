package duoc.levelupgamer.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import duoc.levelupgamer.models.entities.DetalleVentaModel;
import duoc.levelupgamer.models.entities.ProductoModel;
import duoc.levelupgamer.models.entities.VentaModel;
import duoc.levelupgamer.repository.DetalleVentaRepository;
import duoc.levelupgamer.repository.ProductoRepository;
import duoc.levelupgamer.repository.VentaRepository;

@Service
@Transactional
public class DetalleVentaService {

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public List<DetalleVentaModel> listarTodos() {
        return detalleVentaRepository.findAll();
    }

    // Devuelve null si el detalle no existe
    public DetalleVentaModel buscarPorId(Integer id) {
        return detalleVentaRepository.findById(id).orElse(null);
    }

    // Devuelve null si la venta no existe
    public List<DetalleVentaModel> buscarPorVenta(Integer idVenta) {
        VentaModel venta = ventaRepository.findById(idVenta).orElse(null);
        if (venta == null) {
            return null;
        }
        return detalleVentaRepository.findByVenta(venta);
    }

    // Devuelve null si la venta o el producto no existen
    public DetalleVentaModel guardar(DetalleVentaModel detalle) {
        if (detalle.getVenta() == null || detalle.getProducto() == null) {
            return null;
        }
        VentaModel venta = ventaRepository.findById(detalle.getVenta().getId_venta()).orElse(null);
        if (venta == null) {
            return null;
        }
        ProductoModel producto = productoRepository.findById(detalle.getProducto().getCodigo_producto()).orElse(null);
        if (producto == null) {
            return null;
        }
        detalle.setVenta(venta);
        detalle.setProducto(producto);
        // El precio se congela: se copia del producto al momento de la compra
        detalle.setPrecio_unitario(producto.getPrecio_producto());
        return detalleVentaRepository.save(detalle);
    }

    // Devuelve null si el detalle no existe
    public DetalleVentaModel actualizar(Integer id, DetalleVentaModel detalle) {
        DetalleVentaModel existente = detalleVentaRepository.findById(id).orElse(null);
        if (existente == null) {
            return null;
        }
        existente.setCantidad_producto(detalle.getCantidad_producto());
        return detalleVentaRepository.save(existente);
    }

    // Devuelve false si el detalle no existe
    public boolean eliminar(Integer id) {
        if (!detalleVentaRepository.existsById(id)) {
            return false;
        }
        detalleVentaRepository.deleteById(id);
        return true;
    }
}