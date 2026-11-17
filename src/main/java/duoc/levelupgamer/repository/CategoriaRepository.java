package duoc.levelupgamer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import duoc.levelupgamer.models.entities.CategoriaModel;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaModel, Integer> {

}