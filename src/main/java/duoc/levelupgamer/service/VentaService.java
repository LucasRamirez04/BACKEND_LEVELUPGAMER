package duoc.levelupgamer.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import duoc.levelupgamer.models.entities.UsuarioModel;
import duoc.levelupgamer.models.entities.VentaModel;
import duoc.levelupgamer.repository.UsuarioRepository;
import duoc.levelupgamer.repository.VentaRepository;

@Service
@Transactional
public class VentaService {

    private static final Logger log = LoggerFactory.getLogger(VentaService.class);

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<VentaModel> listarTodos() {
        log.info("Listando todas las ventas");
        return ventaRepository.findAll();
    }

    // Devuelve null si la venta no existe
    public VentaModel buscarPorId(Integer id) {
        log.info("Buscando venta con id: {}", id);
        return ventaRepository.findById(id).orElse(null);
    }

    // Devuelve null si no se envio el usuario o el usuario no existe
    public VentaModel guardar(VentaModel venta) {
        log.info("Guardando venta para el usuario id: {}", 
                 venta.getUsuario() != null ? venta.getUsuario().getId_usuario() : null);
        UsuarioModel usuario = buscarUsuario(venta);
        if (usuario == null) {
            return null;
        }
        venta.setUsuario(usuario);
        return ventaRepository.save(venta);
    }

    // Devuelve null si la venta o el usuario no existen
    public VentaModel actualizar(Integer id, VentaModel venta) {
        log.info("Actualizando venta con id: {}", id);
        VentaModel existente = ventaRepository.findById(id).orElse(null);
        if (existente == null) {
            return null;
        }
        UsuarioModel usuario = buscarUsuario(venta);
        if (usuario == null) {
            return null;
        }
        existente.setUsuario(usuario);
        existente.setFecha_venta(venta.getFecha_venta());
        existente.setTotal(venta.getTotal());
        existente.setDireccion_envio(venta.getDireccion_envio());
        return ventaRepository.save(existente);
    }

    // Devuelve false si la venta no existe
    public boolean eliminar(Integer id) {
        log.info("Eliminando venta con id: {}", id);
        if (!ventaRepository.existsById(id)) {
            return false;
        }
        ventaRepository.deleteById(id);
        return true;
    }

    // Busca en la BD el usuario que viene en la venta (null si no viene o no existe)
    private UsuarioModel buscarUsuario(VentaModel venta) {
        if (venta.getUsuario() == null) {
            return null;
        }
        return usuarioRepository.findById(venta.getUsuario().getId_usuario()).orElse(null);
    }
}
