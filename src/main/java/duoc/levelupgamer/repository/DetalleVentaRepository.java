package duoc.levelupgamer.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import duoc.levelupgamer.models.entities.DetalleVentaModel;
import duoc.levelupgamer.models.entities.VentaModel;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVentaModel, Integer> {

    List<DetalleVentaModel> findByVenta(VentaModel venta);
}