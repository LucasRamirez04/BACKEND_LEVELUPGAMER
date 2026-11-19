package duoc.levelupgamer.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String type;
    private long expiresIn;
    private String rol;
    private String nombres;
}