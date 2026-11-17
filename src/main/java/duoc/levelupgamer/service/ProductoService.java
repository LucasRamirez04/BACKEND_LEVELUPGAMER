package duoc.levelupgamer.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import duoc.levelupgamer.models.entities.CategoriaModel;
import duoc.levelupgamer.models.entities.ProductoModel;
import duoc.levelupgamer.repository.CategoriaRepository;
import duoc.levelupgamer.repository.ProductoRepository;

@Service
@Transactional
public class ProductoService {

    private static final Logger log = LoggerFactory.getLogger(ProductoService.class);

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<ProductoModel> listarTodos() {
        log.info("Listando todos los productos");
        return productoRepository.findAll();
    }

    // Devuelve null si el producto no existe
    public ProductoModel buscarPorId(Integer id) {
        log.info("Buscando producto con id: {}", id);
        return productoRepository.findById(id).orElse(null);
    }

    // Devuelve null si la categoria no existe
    public List<ProductoModel> buscarPorCategoria(Integer idCategoria) {
        log.info("Buscando productos de la categoria: {}", idCategoria);
        CategoriaModel categoria = categoriaRepository.findById(idCategoria).orElse(null);
        if (categoria == null) {
            return null;
        }
        return productoRepository.findByCategoria(categoria);
    }

    // Devuelve null si no se envio la categoria o la categoria no existe
    public ProductoModel guardar(ProductoModel producto) {
        log.info("Guardando producto: {}", producto.getNombre_producto());
        CategoriaModel categoria = buscarCategoria(producto);
        if (categoria == null) {
            return null;
        }
        producto.setCategoria(categoria);
        return productoRepository.save(producto);
    }

    // Devuelve null si el producto o la categoria no existen
    public ProductoModel actualizar(Integer id, ProductoModel producto) {
        log.info("Actualizando producto con id: {}", id);
        ProductoModel existente = productoRepository.findById(id).orElse(null);
        if (existente == null) {
            return null;
        }
        CategoriaModel categoria = buscarCategoria(producto);
        if (categoria == null) {
            return null;
        }
        existente.setCategoria(categoria);
        existente.setNombre_producto(producto.getNombre_producto());
        existente.setDescripcion_producto(producto.getDescripcion_producto());
        existente.setPrecio_producto(producto.getPrecio_producto());
        existente.setPorcentaje_producto(producto.getPorcentaje_producto());
        existente.setStock_producto(producto.getStock_producto());
        existente.setImagen_producto(producto.getImagen_producto());
        return productoRepository.save(existente);
    }

    // Devuelve false si el producto no existe
    public boolean eliminar(Integer id) {
        log.info("Eliminando producto con id: {}", id);
        if (!productoRepository.existsById(id)) {
            return false;
        }
        productoRepository.deleteById(id);
        return true;
    }

    // Busca en la BD la categoria que viene en el body (null si no viene o no existe)
    private CategoriaModel buscarCategoria(ProductoModel producto) {
        if (producto.getCategoria() == null) {
            return null;
        }
        return categoriaRepository.findById(producto.getCategoria().getId_categoria()).orElse(null);
    }
}