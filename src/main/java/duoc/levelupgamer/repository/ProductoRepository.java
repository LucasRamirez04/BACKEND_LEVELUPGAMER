package duoc.levelupgamer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import duoc.levelupgamer.models.ProductoModel;

public interface ProductoRepository extends JpaRepository<ProductoModel, Integer> {
    
}
