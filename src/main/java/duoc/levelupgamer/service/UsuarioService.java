package duoc.levelupgamer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import duoc.levelupgamer.models.entities.UsuarioModel;
import duoc.levelupgamer.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UsuarioModel> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<UsuarioModel> obtenerPorId(int id) {
        return usuarioRepository.findById(id);
    }

    public UsuarioModel crearUsuario(UsuarioModel usuario) {
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        if (usuario.getRol() == null || usuario.getRol().isBlank()) {
            usuario.setRol("Cliente");
        }
        return usuarioRepository.save(usuario);
    }

    public Optional<UsuarioModel> actualizarUsuario(int id, UsuarioModel datos) {
        return usuarioRepository.findById(id).map(u -> {
            u.setRun(datos.getRun());
            u.setNombres(datos.getNombres());
            u.setApellidos(datos.getApellidos());
            u.setCorreo(datos.getCorreo());
            u.setFecha_nacimiento(datos.getFecha_nacimiento());
            u.setDireccion(datos.getDireccion());
            u.setRol(datos.getRol());

            // Solo vuelve a encriptar si se envió una nueva contraseña
            if (datos.getContrasena() != null && !datos.getContrasena().isBlank()) {
                u.setContrasena(passwordEncoder.encode(datos.getContrasena()));
            }
            return usuarioRepository.save(u);
        });
    }

    public boolean eliminarUsuario(int id) {
        if (!usuarioRepository.existsById(id)) {
            return false;
        }
        usuarioRepository.deleteById(id);
        return true;
    }
}