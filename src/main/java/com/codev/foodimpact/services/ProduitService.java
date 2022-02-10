package com.codev.foodimpact.services;

import com.codev.foodimpact.entities.Produit;
import com.codev.foodimpact.repositories.ProduitRepository;
import com.codev.foodimpact.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {
    private ProduitRepository produitRepository;

    @Autowired
    public ProduitService(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    public Optional<Produit> getByCodebarre(Long codebarre){
        return produitRepository.findById(codebarre);
    }

    public boolean saveProduit(Produit produit) {
        if(produit.getCodebarre() != null){
            produitRepository.save(produit);
            return true;
        }else return false;
    }

    public List<Produit> getAll() {
        return produitRepository.findAll();
    }
}
