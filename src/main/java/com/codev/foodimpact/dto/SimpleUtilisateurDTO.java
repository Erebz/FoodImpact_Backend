package com.codev.foodimpact.dto;


import com.codev.foodimpact.entities.Utilisateur;

public class SimpleUtilisateurDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String mail;

    public Long getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getMail() { return mail; }

    public SimpleUtilisateurDTO(Utilisateur utilisateur){
        id = utilisateur.getId();
        nom = utilisateur.getNom();
        prenom = utilisateur.getPrenom();
        mail = utilisateur.getMail();
    }
}
