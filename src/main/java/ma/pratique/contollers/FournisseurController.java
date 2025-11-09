package ma.pratique.contollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ma.pratique.entities.Fournisseur;
import ma.pratique.repositories.FournisseurRepository;

@RestController
@RequestMapping("/api/fournisseurs")
public class FournisseurController {

    @Autowired
    private FournisseurRepository fournisseurRepository;

    // Lister tous les fournisseurs
    @GetMapping
    public List<Fournisseur> getAll() {
        return fournisseurRepository.findAll();
    }

    // Récupérer un fournisseur par id
    @GetMapping("/{id}")
    public ResponseEntity<Fournisseur> getById(@PathVariable Long id) {
        return fournisseurRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Ajouter un nouveau fournisseur
    @PostMapping
    public ResponseEntity<Fournisseur> create(@RequestBody Fournisseur f) {
        Fournisseur saved = fournisseurRepository.save(f);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // Modifier un fournisseur existant
    @PutMapping("/{id}")
    public ResponseEntity<Fournisseur> update(@PathVariable Long id, @RequestBody Fournisseur f) {
        return fournisseurRepository.findById(id)
                .map(existing -> {
                    f.setId(id);
                    Fournisseur updated = fournisseurRepository.save(f);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Supprimer un fournisseur
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return fournisseurRepository.findById(id)
                .map(existing -> {
                    fournisseurRepository.delete(existing);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
