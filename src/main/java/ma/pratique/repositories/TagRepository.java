package ma.pratique.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.pratique.entities.Tag;


public interface TagRepository extends JpaRepository<Tag, Long> {}