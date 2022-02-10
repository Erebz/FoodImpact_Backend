package com.codev.foodimpact.controllers;

import com.codev.foodimpact.dto.DetailedUtilisateurDTO;
import com.codev.foodimpact.entities.Favori;
import com.codev.foodimpact.entities.Produit;
import com.codev.foodimpact.entities.Utilisateur;
import com.codev.foodimpact.services.FavoriService;
import com.codev.foodimpact.services.JwtUserDetailsService;
import com.codev.foodimpact.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    @Autowired
    private JwtUserDetailsService utilisateurService;
    @Autowired
    private ProduitService produitService;
    @Autowired
    private FavoriService favoriService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUtilisateur(@PathVariable("id") long id){
        Optional<Utilisateur> _util = utilisateurService.getUtilisateurFromId(id);
        if(_util.isPresent()){
            DetailedUtilisateurDTO utilisateurDTO = new DetailedUtilisateurDTO(_util.get());
            return ResponseEntity.ok(utilisateurDTO);
        }else return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/favoris")
    public ResponseEntity<?> getFavoris(@PathVariable("id") long id){
        Optional<Utilisateur> _util = utilisateurService.getUtilisateurFromId(id);
        if(_util.isPresent()){
            DetailedUtilisateurDTO utilisateurDTO = new DetailedUtilisateurDTO(_util.get());
            return ResponseEntity.ok(utilisateurDTO.getFavoris());
        }else return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/ajouterFavori/{codebarre}")
    public ResponseEntity<?> ajouterFavori(@PathVariable("id") long id, @PathVariable("codebarre") long codebarre){
        Optional<Utilisateur> _util = utilisateurService.getUtilisateurFromId(id);
        Optional<Produit> _prod = produitService.getByCodebarre(codebarre);
        if(_prod.isPresent() && _util.isPresent()){
            favoriService.ajouterFavori(_util.get(), _prod.get());
            return ResponseEntity.ok().build();
        }else return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/retirerFavori/{codebarre}")
    public ResponseEntity<?> retirerFavori(@PathVariable("id") long id, @PathVariable("codebarre") long codebarre){
        Optional<Utilisateur> _util = utilisateurService.getUtilisateurFromId(id);
        Optional<Produit> _prod = produitService.getByCodebarre(codebarre);
        if(_prod.isPresent() && _util.isPresent()){
            Optional<Favori> _fav = favoriService.getByUtilisateurAndProduit(_util.get(), _prod.get());
            if(_fav.isPresent()){
                favoriService.retirerFavori(_fav.get());
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.notFound().build();
    }
}
