package ma.pratique.entities;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;
    private Double prix;
    private String codeBarre;
    private LocalDate dateExpiration;

    @ManyToOne
    private Categorie categorie;

    @ManyToOne
    private Fournisseur fournisseur;

    @OneToOne(cascade = CascadeType.ALL)
    private DetailProduit detailProduit;

    @ManyToMany
    @JoinTable(
        name = "produit_tag",
        joinColumns = @JoinColumn(name = "produit_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    
    private Set<Tag> tags;

    // Constructeur vide
    public Produit() {}

    // Constructeur complet
    public Produit(Long id, String nom, String description, Double prix, String codeBarre, LocalDate dateExpiration,
                   Categorie categorie, Fournisseur fournisseur, DetailProduit detailProduit, Set<Tag> tags) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.codeBarre = codeBarre;
        this.dateExpiration = dateExpiration;
        this.categorie = categorie;
        this.fournisseur = fournisseur;
        this.detailProduit = detailProduit;
        this.tags = tags;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getPrix() { return prix; }
    public void setPrix(Double prix) { this.prix = prix; }

    public String getCodeBarre() { return codeBarre; }
    public void setCodeBarre(String codeBarre) { this.codeBarre = codeBarre; }

    public LocalDate getDateExpiration() { return dateExpiration; }
    public void setDateExpiration(LocalDate dateExpiration) { this.dateExpiration = dateExpiration; }

    public Categorie getCategorie() { return categorie; }
    public void setCategorie(Categorie categorie) { this.categorie = categorie; }

    public Fournisseur getFournisseur() { return fournisseur; }
    public void setFournisseur(Fournisseur fournisseur) { this.fournisseur = fournisseur; }

    public DetailProduit getDetailProduit() { return detailProduit; }
    public void setDetailProduit(DetailProduit detailProduit) { this.detailProduit = detailProduit; }

    public Set<Tag> getTags() { return tags; }
    public void setTags(Set<Tag> tags) { this.tags = tags; }
}
