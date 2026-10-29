package duoc.levelupgamer.models;

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
@Table(name = "venta")
public class VentaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_venta;

        @ManyToOne 
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioModel usuario;
    
    
    @Column (nullable = false)
    private String fecha_venta;

    @Column (nullable = false)
    private int total;

    @Column (nullable = false)
    private String direccion_envio;

}
