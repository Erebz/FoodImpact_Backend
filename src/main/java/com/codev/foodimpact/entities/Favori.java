package com.codev.foodimpact.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "favori", schema = "foodimpact", catalog = "")
public class Favori {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne
  @JsonBackReference("util-fav")
  @JoinColumn(name = "utilisateur", referencedColumnName = "id", nullable = false)
  private Utilisateur utilisateur;

  @ManyToOne
  @JsonBackReference("prod-fav")
  @JoinColumn(name = "codebarre", referencedColumnName = "codebarre", nullable = false)
  private Produit codebarre;

  // Getters, setters
  public long getId() {
    return id;
  }
  public void setId(long id) {
    this.id = id;
  }

  public Utilisateur getUtilisateur() {
    return utilisateur;
  }
  public void setUtilisateur(Utilisateur utilisateur) {
    this.utilisateur = utilisateur;
  }

  public Produit getCodebarre() {
    return codebarre;
  }
  public void setCodebarre(Produit Produit) { this.codebarre = Produit; }

}
