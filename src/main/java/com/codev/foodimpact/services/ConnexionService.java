package com.codev.foodimpact.services;

import com.codev.foodimpact.entities.Utilisateur;
import com.codev.foodimpact.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConnexionService implements IConnexionService{
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    public ConnexionService(UtilisateurRepository UtilisateurRepository) {
        this.utilisateurRepository = UtilisateurRepository;
    }

    @Override
    public Optional<Utilisateur> authentification(String mail, String mdp) {
        return utilisateurRepository.findById(1L);
    }
}
