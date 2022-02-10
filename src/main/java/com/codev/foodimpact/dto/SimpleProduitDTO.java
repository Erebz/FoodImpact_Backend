package com.codev.foodimpact.dto;

import com.codev.foodimpact.entities.Produit;

public class SimpleProduitDTO {
    private Long code;
    private String nom;
    private String categories;
    private Integer nbFavs;

    public String getNom() { return nom; }
    public Long getCode() { return code; }
    public String getCategories() { return categories; }
    public Integer getNbFavs() { return nbFavs; }

    public SimpleProduitDTO(Produit produit){
        code = produit.getCodebarre();
        nom = produit.getNom();
        categories = produit.getCategorie();
        nbFavs = 0;
        if (produit.getFavoris() != null) nbFavs = produit.getFavoris().size();
    }
}
