package com.codev.foodimpact.services;

import com.codev.foodimpact.entities.Favori;
import com.codev.foodimpact.entities.Produit;
import com.codev.foodimpact.entities.Utilisateur;
import com.codev.foodimpact.repositories.FavoriRespository;
import com.codev.foodimpact.repositories.ProduitRepository;
import com.codev.foodimpact.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class FavoriService {
    private ProduitRepository produitRepository;
    private UtilisateurRepository utilisateurRepository;
    private FavoriRespository favoriRespository;

    @Autowired
    public FavoriService(UtilisateurRepository utilisateurRepository, ProduitRepository produitRepository, FavoriRespository favoriRespository) {
        this.utilisateurRepository = utilisateurRepository;
        this.produitRepository = produitRepository;
        this.favoriRespository = favoriRespository;
    }

    public void ajouterFavori(Utilisateur util, Produit prod){
        Favori favori = new Favori();
        if(getByUtilisateurAndProduit(util, prod).isEmpty()){
            favori.setUtilisateur(util);
            favori.setCodebarre(prod);
            ajouterFavori(favori);
        }
    }

    @Modifying
    @Transactional
    public void ajouterFavori(Favori favori){
        this.favoriRespository.save(favori);
    }

    @Modifying
    @Transactional
    public void retirerFavori(Favori favori){
        this.favoriRespository.delete(favori);
    }

    public Optional<Favori> getByUtilisateurAndProduit(Utilisateur utilisateur, Produit produit) {
        Favori favori = this.favoriRespository.findFirstByUtilisateurAndCodebarre(utilisateur, produit);
        return Optional.ofNullable(favori);
    }
}
