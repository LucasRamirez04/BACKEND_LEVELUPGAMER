package duoc.levelupgamer.service;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import duoc.levelupgamer.models.entities.CategoriaModel;
import duoc.levelupgamer.repository.CategoriaRepository;

@Service
@Transactional
public class CategoriaService {

    private static final Logger log = LoggerFactory.getLogger(CategoriaService.class);

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<CategoriaModel> listarTodos() {
        log.info("Listando todas las categorias");
        return categoriaRepository.findAll();
    }

    // Devuelve null si la categoria no existe
    public CategoriaModel buscarPorId(Integer id) {
        log.info("Buscando categoria con id: {}", id);
        return categoriaRepository.findById(id).orElse(null);
    }

    public CategoriaModel guardar(CategoriaModel categoria) {
        log.info("Guardando categoria: {}", categoria.getNombre_categoria());
        return categoriaRepository.save(categoria);
    }

    // Devuelve null si la categoria no existe
    public CategoriaModel actualizar(Integer id, CategoriaModel categoria) {
        log.info("Actualizando categoria con id: {}", id);
        CategoriaModel existente = categoriaRepository.findById(id).orElse(null);
        if (existente == null) {
            return null;
        }
        existente.setNombre_categoria(categoria.getNombre_categoria());
        return categoriaRepository.save(existente);
    }

    // Devuelve false si la categoria no existe
    public boolean eliminar(Integer id) {
        log.info("Eliminando categoria con id: {}", id);
        if (!categoriaRepository.existsById(id)) {
            return false;
        }
        categoriaRepository.deleteById(id);
        return true;
    }
}