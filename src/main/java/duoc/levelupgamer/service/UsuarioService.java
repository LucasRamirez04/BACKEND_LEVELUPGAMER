package duoc.levelupgamer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duoc.levelupgamer.models.UsuarioModel;
import duoc.levelupgamer.repository.UsuarioRepository;

@Service 
public class UsuarioService {
    @Autowired 
    private UsuarioRepository usuarioRepo;

    public List<UsuarioModel> obtenerUsuarios(){
        return usuarioRepo.findAll();   
    }
}
