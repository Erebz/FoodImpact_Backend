package com.codev.foodimpact.entities;

import com.codev.foodimpact.validations.ValidAjoutUtilisateur;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "utilisateur", schema = "foodimpact", catalog = "")
public class Utilisateur implements Serializable {

  @Id
  @Null(groups = ValidAjoutUtilisateur.class)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Basic
  @NotNull(groups = ValidAjoutUtilisateur.class)
  @Column(name = "nom", nullable = false)
  private String nom;

  @Basic
  @NotNull(groups = ValidAjoutUtilisateur.class)
  @Column(name = "prenom", nullable = false)
  private String prenom;

  @Basic
  @NotNull(groups = ValidAjoutUtilisateur.class)
  @Column(name = "mdp", nullable = false)
  private String mdp;

  @Basic
  @NotNull(groups = ValidAjoutUtilisateur.class)
  @Column(name = "mail", nullable = false)
  private String mail;

  @OneToMany(mappedBy = "utilisateur")
  @Null(groups = ValidAjoutUtilisateur.class)
  @JsonManagedReference("util-fav")
  private List<Favori> favoris;

  // Getters, setters
  public Long getId() { return id; }
  public void setId(Long id) {
    this.id = id;
  }

  public String getNom() {
    return nom;
  }
  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getPrenom() {
    return prenom;
  }
  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  public String getMdp() {
    return mdp;
  }
  public void setMdp(String mdp) {
    this.mdp = mdp;
  }

  public String getMail() {
    return mail;
  }
  public void setMail(String mail) {
    this.mail = mail;
  }

  public List<Favori> getFavoris(){ return this.favoris;}
  public void setFavoris(List<Favori> favoris){ this.favoris = favoris;}

  @Override
  public int hashCode() {
    return Objects.hash(id, nom, prenom, mail, mdp);
  }
}

