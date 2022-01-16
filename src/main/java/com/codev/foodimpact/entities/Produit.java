package com.codev.foodimpact.entities;

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
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long codebarre;

  @Basic
  @Column(name = "nom", insertable =false, updatable=false, nullable = false)
  private String nom;

  @Basic
  @Column(name = "categorie", insertable =false, updatable=false, nullable = false)
  private String categorie;

  @OneToMany(mappedBy = "codebarre")
  private List<Favori> favoris = new ArrayList<Favori>();


  // Getters, setters
  public long getCodebarre() { return codebarre; }
  public void setCodebarre(long codebarre) { this.codebarre = codebarre; }

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
