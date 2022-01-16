package com.codev.foodimpact.controllers;

import com.codev.foodimpact.entities.Utilisateur;
import com.codev.foodimpact.services.ConnexionService;
import com.codev.foodimpact.util.ConfigurationURLs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/login")
public class ConnexionController {
    @Autowired
    private ConnexionService connexionService;

    @PostMapping(value = "/checkLogin")
    public ResponseEntity<?> authentification(@RequestParam("mail") String mail, @RequestParam("mdp") String mdp) {
        Optional<Utilisateur> utilisateur = connexionService.authentification(mail, mdp);
        if(utilisateur.isPresent()) return ResponseEntity.ok(utilisateur.get());
        else return ResponseEntity.notFound().build();
    }

}
