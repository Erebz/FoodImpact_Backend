package com.codev.foodimpact.services;

import com.codev.foodimpact.entities.Utilisateur;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public interface IConnexionService {
    public Optional<Utilisateur> authentification(@RequestParam("mail") String mail, @RequestParam("mdp") String mdp);
}
