package duoc.levelupgamer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import duoc.levelupgamer.models.entities.UsuarioModel;

@Repository 
public interface UsuarioRepository extends JpaRepository<UsuarioModel,Integer> {

}
