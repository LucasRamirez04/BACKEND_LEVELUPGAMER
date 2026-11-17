package duoc.levelupgamer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import duoc.levelupgamer.models.entities.VentaModel;

@Repository
public interface VentaRepository extends JpaRepository<VentaModel, Integer> {

}
