package duoc.levelupgamer.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "detalle_venta")
public class DetalleVentaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_detalle_venta;

    @ManyToOne
    @JoinColumn(name = "id_venta", nullable = false)
    private VentaModel venta;

    @ManyToOne
    @JoinColumn(name = "codigo_producto", nullable = false)
    private ProductoModel producto;

    @Column(nullable = false)
    private int cantidad_producto;

    // Precio congelado al momento del pago
    @Column(nullable = false)
    private int precio_unitario;
}