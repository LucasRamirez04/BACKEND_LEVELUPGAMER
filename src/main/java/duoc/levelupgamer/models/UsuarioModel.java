package duoc.levelupgamer.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Entity 
@Data 
@Table(name = "usuario")
public class UsuarioModel {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_usuario;

    @Column(nullable = false) 
    private String run;

    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String apellidos;
 
    @Column(nullable = false)
    @Email 
    private String correo;
 
    @Column(nullable = false)
    private String contrasena;
 
    @Column(nullable = false)
    private String fecha_nacimiento;
 
    @Column(nullable = false)
    private String direccion;
 
}