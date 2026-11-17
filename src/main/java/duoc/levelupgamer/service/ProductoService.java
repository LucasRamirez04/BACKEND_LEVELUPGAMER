package duoc.levelupgamer.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import duoc.levelupgamer.models.entities.CategoriaModel;
import duoc.levelupgamer.models.entities.ProductoModel;
import duoc.levelupgamer.repository.ProductoRepository;

@Service
@Transactional
public class ProductoService {

    private static final Logger log = LoggerFactory.getLogger(ProductoService.class);

    @Autowired 
    private ProductoRepository productoRepository;

    // Se usa el service de categoria para validar que la categoria exista
    @Autowired
    private CategoriaService categoriaService;

    public List<ProductoModel> listarTodos() {
        log.info("Listando todos los productos");
        return productoRepository.findAll();
    }

    public ProductoModel buscarPorId(Integer id) {
        log.info("Buscando producto con id: {}", id);
        return productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNoEncontradoException("No se encontro el producto con id: " + id));
    }

    public List<ProductoModel> buscarPorCategoria(Integer idCategoria) {
        log.info("Buscando productos de la categoria: {}", idCategoria);
        CategoriaModel categoria = categoriaService.buscarPorId(idCategoria);
        return productoRepository.findByCategoria(categoria);
    }

    public ProductoModel crearDesdeRequest(ProductoRequest request) {
        log.info("Creando producto con nombre: {}", request.getNombre_producto());
        ProductoModel producto = new ProductoModel();
        copiarDatos(producto, request);
        return productoRepository.save(producto);
    }

    public ProductoModel actualizar(Integer id, ProductoRequest request) {
        log.info("Actualizando producto con id: {}", id);
        ProductoModel producto = buscarPorId(id);
        copiarDatos(producto, request);
        return productoRepository.save(producto);
    }

    public void eliminar(Integer id) {
        log.info("Eliminando producto con id: {}", id);
        ProductoModel producto = buscarPorId(id);
        productoRepository.delete(producto);
    }

    // Pasa los datos del request al modelo (lo usan crear y actualizar)
    private void copiarDatos(ProductoModel producto, ProductoRequest request) {
        producto.setCategoria(categoriaService.buscarPorId(request.getId_categoria()));
        producto.setNombre_producto(request.getNombre_producto());
        producto.setDescripcion_producto(request.getDescripcion_producto());
        producto.setPrecio_producto(request.getPrecio_producto());
        producto.setPorcentaje_producto(request.getPorcentaje_producto());
        producto.setStock_producto(request.getStock_producto());
        producto.setImagen_producto(request.getImagen_producto());
    }
}