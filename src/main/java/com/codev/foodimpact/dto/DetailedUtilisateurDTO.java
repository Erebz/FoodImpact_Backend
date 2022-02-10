package com.codev.foodimpact.dto;

import com.codev.foodimpact.entities.Favori;
import com.codev.foodimpact.entities.Produit;
import com.codev.foodimpact.entities.Utilisateur;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DetailedUtilisateurDTO extends SimpleUtilisateurDTO{
    private List<SimpleProduitDTO> favoris;

    public List<SimpleProduitDTO> getFavoris() { return favoris; }

    public DetailedUtilisateurDTO(Utilisateur utilisateur){
        super(utilisateur);
        favoris = utilisateur.getFavoris().stream().map(Favori::getCodebarre).map(SimpleProduitDTO::new).collect(Collectors.toList());
    }
}
