package ma.pratique.contollers;

import ma.pratique.entities.Categorie;
import ma.pratique.entities.Fournisseur;
import ma.pratique.entities.Produit;
import ma.pratique.repositories.CategorieRepository;
import ma.pratique.repositories.FournisseurRepository;
import ma.pratique.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DashboardController {

    @Autowired
    private CategorieRepository categorieRepository;

    @Autowired
    private FournisseurRepository fournisseurRepository;

    @Autowired
    private ProduitRepository produitRepository;

    // 🏠 Page principale du dashboard
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("categories", categorieRepository.findAll());
        model.addAttribute("fournisseurs", fournisseurRepository.findAll());
        model.addAttribute("produits", produitRepository.findAll());
        return "dashboard";
    }

    // ===================== CATEGORIES =====================

    @PostMapping("/categories/add")
    public String addCategorie(@RequestParam String nom,
                               @RequestParam String description) {
        Categorie c = new Categorie();
        c.setNom(nom);
        c.setDescription(description);
        categorieRepository.save(c);
        return "redirect:/dashboard";
    }

    @PostMapping("/categories/edit/{id}")
    public String editCategorie(@PathVariable Long id,
                                @RequestParam String nom,
                                @RequestParam String description) {
        categorieRepository.findById(id).ifPresent(c -> {
            c.setNom(nom);
            c.setDescription(description);
            categorieRepository.save(c);
        });
        return "redirect:/dashboard";
    }

    @GetMapping("/categories/delete/{id}")
    public String deleteCategorie(@PathVariable Long id) {
        categorieRepository.deleteById(id);
        return "redirect:/dashboard";
    }

    // ===================== FOURNISSEURS =====================

    @PostMapping("/fournisseurs/add")
    public String addFournisseur(@RequestParam String nom,
                                 @RequestParam String contact,
                                 @RequestParam String adresse) {
        Fournisseur f = new Fournisseur();
        f.setNom(nom);
        f.setContact(contact);
        f.setAdresse(adresse);
        fournisseurRepository.save(f);
        return "redirect:/dashboard";
    }

    @PostMapping("/fournisseurs/edit/{id}")
    public String editFournisseur(@PathVariable Long id,
                                  @RequestParam String nom,
                                  @RequestParam String contact,
                                  @RequestParam String adresse) {
        fournisseurRepository.findById(id).ifPresent(f -> {
            f.setNom(nom);
            f.setContact(contact);
            f.setAdresse(adresse);
            fournisseurRepository.save(f);
        });
        return "redirect:/dashboard";
    }

    @GetMapping("/fournisseurs/delete/{id}")
    public String deleteFournisseur(@PathVariable Long id) {
        fournisseurRepository.deleteById(id);
        return "redirect:/dashboard";
    }

    // ===================== PRODUITS =====================

    @PostMapping("/produits/add")
    public String addProduit(@RequestParam String nom,
                             @RequestParam double prix,
                             @RequestParam String codeBarre,
                             @RequestParam Long categorieId,
                             @RequestParam Long fournisseurId) {

        Categorie cat = categorieRepository.findById(categorieId).orElse(null);
        Fournisseur f = fournisseurRepository.findById(fournisseurId).orElse(null);

        if (cat != null && f != null) {
            Produit p = new Produit();
            p.setNom(nom);
            p.setPrix(prix);
            p.setCodeBarre(codeBarre);
            p.setCategorie(cat);
            p.setFournisseur(f);
            produitRepository.save(p);
        }

        return "redirect:/dashboard";
    }

    @PostMapping("/produits/edit/{id}")
    public String editProduit(@PathVariable Long id,
                              @RequestParam String nom,
                              @RequestParam double prix,
                              @RequestParam String codeBarre,
                              @RequestParam Long categorieId,
                              @RequestParam Long fournisseurId) {

        produitRepository.findById(id).ifPresent(p -> {
            Categorie cat = categorieRepository.findById(categorieId).orElse(null);
            Fournisseur f = fournisseurRepository.findById(fournisseurId).orElse(null);
            if (cat != null && f != null) {
                p.setNom(nom);
                p.setPrix(prix);
                p.setCodeBarre(codeBarre);
                p.setCategorie(cat);
                p.setFournisseur(f);
                produitRepository.save(p);
            }
        });
        return "redirect:/dashboard";
    }

    @GetMapping("/produits/delete/{id}")
    public String deleteProduit(@PathVariable Long id) {
        produitRepository.deleteById(id);
        return "redirect:/dashboard";
    }
}
