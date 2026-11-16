package duoc.levelupgamer.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import duoc.levelupgamer.models.ProductoModel;
import duoc.levelupgamer.repository.ProductoRepository;

@Service 
public class ProductoService {
    @Autowired 
    private ProductoRepository productoRepository;

    public List<ProductoModel> obtenerUsuarios(){
        return productoRepository.findAll();   
    }

    
}
