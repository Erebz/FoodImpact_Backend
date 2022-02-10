package com.codev.foodimpact.controllers;

import com.codev.foodimpact.config.JwtTokenUtil;
import com.codev.foodimpact.entities.Utilisateur;
import com.codev.foodimpact.services.JwtUserDetailsService;
import com.codev.foodimpact.validations.ValidAjoutUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Optional;

@RequestMapping("/authentification")
@RestController
@CrossOrigin
public class AuthentificationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody Utilisateur util) {
        Optional<UserDetails> userDetails = appelAuthentication(util.getMail(), util.getMdp());
        Optional<Utilisateur> _util = userDetailsService.getUtilisateurFromMail(util.getMail());
        if(userDetails.isPresent() && _util.isPresent()){
            final String token = jwtTokenUtil.generateToken(userDetails.get());

            // On renvoit le token
            HashMap<String, String> json = new HashMap<String, String>();
            json.put("id", ""+ _util.get().getId());
            json.put("token", token);

            return ResponseEntity.ok(json);
        }else
            return ResponseEntity.notFound().build();
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Validated({ValidAjoutUtilisateur.class}) @RequestBody Utilisateur util){
        Optional<Utilisateur> _util = userDetailsService.getUtilisateurFromMail(util.getMail());
        if(_util.isEmpty()){
            userDetailsService.register(util);
            return ResponseEntity.ok().build();
        }else
            return ResponseEntity.unprocessableEntity().build();
    }

    private Optional<UserDetails> appelAuthentication(String mail, String password) {
        UserDetails userDetails;
        try{
            Authentication  authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(mail, password));
            userDetails = (UserDetails) authentication.getPrincipal();
        }catch (Exception e){
            userDetails = null;
        }
        return Optional.ofNullable(userDetails);
    }

}
