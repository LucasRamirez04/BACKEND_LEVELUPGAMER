package duoc.levelupgamer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import duoc.levelupgamer.models.dto.AuthResponse;
import duoc.levelupgamer.models.dto.LoginRequest;
import duoc.levelupgamer.models.entities.UsuarioModel;
import duoc.levelupgamer.repository.UsuarioRepository;
import duoc.levelupgamer.security.JwtService;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    // Devuelve false si el correo ya existe
    public boolean registrar(UsuarioModel nuevoUsuario) {
        if (usuarioRepository.existsByCorreo(nuevoUsuario.getCorreo())) {
            return false;
        }
        
        // Encriptamos la contraseña antes de guardar en la base de datos
        nuevoUsuario.setContrasena(passwordEncoder.encode(nuevoUsuario.getContrasena()));
        
        // Si viene sin rol desde el formulario de registro, le asignamos "Cliente" por defecto
        if (nuevoUsuario.getRol() == null || nuevoUsuario.getRol().isBlank()) {
            nuevoUsuario.setRol("Cliente");
        }
        
        usuarioRepository.save(nuevoUsuario);
        return true;
    }

    // Devuelve null si las credenciales no son válidas
    public AuthResponse login(LoginRequest request) {
        UsuarioModel usuario = usuarioRepository.findByCorreo(request.getCorreo()).orElse(null);
        
        // Comparamos la contraseña ingresada contra el hash guardado en BD
        if (usuario == null || !passwordEncoder.matches(request.getContrasena(), usuario.getContrasena())) {
            return null;
        }
        
        String token = jwtService.generarToken(usuario.getCorreo(), usuario.getRol());
        return new AuthResponse(
                token, 
                "Bearer", 
                jwtService.getExpiracionSegundos(), 
                usuario.getRol(), 
                usuario.getNombres()
        );
    }
}