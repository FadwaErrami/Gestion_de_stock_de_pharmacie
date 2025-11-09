package ma.pratique.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import ma.pratique.entities.Fournisseur;


public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {}