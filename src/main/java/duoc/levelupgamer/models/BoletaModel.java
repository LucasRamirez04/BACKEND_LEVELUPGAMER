package duoc.levelupgamer.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity 
@Data
@Table (name = "boleta")
public class BoletaModel {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_boleta;

    @OneToOne 
    @JoinColumn(name = "id_usuario", nullable = false)
    private VentaModel venta;

    @Column (nullable = false)
    private String fecha_emision;

    @Column (nullable = false)
    private String metodo_pago;


}
