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
@Table (name = "producto")
public class ProductoModel {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int codigo_producto;
    
    @ManyToOne 
    @JoinColumn (name = "id_categoria", nullable = false)
    private CategoriaModel categoria;

    @Column (nullable = false)
    private String nombre_producto;

    @Column (nullable = false)
    private String descripcion_producto;

    @Column (nullable = false)
    private int precio_producto;

    @Column (nullable = false)
    private int porcentaje_producto;

    @Column (nullable = false)
    private int stock_producto;

    @Column (nullable = false)
    private String imagen_producto;
    
}
