package ma.pratique.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.pratique.entities.DetailProduit;


public interface DetailProduitRepository extends JpaRepository<DetailProduit, Long> {}