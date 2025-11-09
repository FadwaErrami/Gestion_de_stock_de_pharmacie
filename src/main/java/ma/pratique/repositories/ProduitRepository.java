package ma.pratique.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.pratique.entities.Produit;


public interface ProduitRepository extends JpaRepository<Produit, Long> {}