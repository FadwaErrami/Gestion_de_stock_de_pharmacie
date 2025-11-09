package ma.pratique.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ma.pratique.entities.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
	
}
