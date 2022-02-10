package com.codev.foodimpact.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "produit", schema = "foodimpact", catalog = "")
public class Produit {

  @Id
  private Long codebarre;

  @Basic
  @Column(name = "nom", nullable = false)
  private String nom;

  @Basic
  @Column(name = "categorie", nullable = false)
  private String categorie;

  @OneToMany(mappedBy = "codebarre")
  @JsonManagedReference("prod-fav")
  private List<Favori> favoris = new ArrayList<Favori>();


  // Getters, setters
  public Long getCodebarre() { return codebarre; }
  public void setCodebarre(Long codebarre) { this.codebarre = codebarre; }

  public String getNom() {
    return nom;
  }
  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getCategorie() {
    return categorie;
  }
  public void setCategorie(String categorie) {
    this.categorie = categorie;
  }

  public List<Favori> getFavoris(){ return this.favoris;}
  public void setFavoris(List<Favori> favoris){ this.favoris = favoris;}

}
