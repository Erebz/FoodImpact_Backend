package com.codev.foodimpact.repositories;

import com.codev.foodimpact.entities.Favori;
import com.codev.foodimpact.entities.Produit;
import com.codev.foodimpact.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriRespository extends JpaRepository<Favori, Long> {
    public Favori findFirstByUtilisateurAndCodebarre(Utilisateur utilisateur, Produit produit);
}
