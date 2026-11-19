package duoc.levelupgamer.models.dto;

import lombok.Data;

@Data 
public class LoginRequest {
    private String correo;
    private String contrasena;
}