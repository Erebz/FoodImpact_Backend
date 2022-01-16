package com.codev.foodimpact.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "utilisateur", schema = "foodimpact", catalog = "")
public class Utilisateur implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Basic
  @Column(name = "nom", insertable =false, updatable=false, nullable = false)
  private String nom;

  @Basic
  @Column(name = "prenom", insertable =false, updatable=false, nullable = false)
  private String prenom;

  @Basic
  @Column(name = "mdp", insertable =false, updatable=false, nullable = false)
  private String mdp;

  @Basic
  @Column(name = "mail", insertable =false, updatable=false, nullable = false)
  private String mail;

  @OneToMany(mappedBy = "utilisateur")
  private List<Favori> favoris = new ArrayList<Favori>();

  // Getters, setters
  public long getId() { return id; }
  public void setId(long id) {
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

