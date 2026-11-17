package duoc.levelupgamer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import duoc.levelupgamer.models.entities.CategoriaModel;
import duoc.levelupgamer.models.entities.ProductoModel;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoModel, Integer> {

    List<ProductoModel> findByCategoria(CategoriaModel categoria);
}