package com.codev.foodimpact.services;

import com.codev.foodimpact.config.JwtTokenUtil;
import com.codev.foodimpact.entities.Utilisateur;
import com.codev.foodimpact.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private UtilisateurRepository utilisateurRepository;
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    public JwtUserDetailsService(UtilisateurRepository UtilisateurRepository, JwtTokenUtil JwtTokenUtil) {
        this.utilisateurRepository = UtilisateurRepository;
        this.jwtTokenUtil = JwtTokenUtil;
    }

    public Optional<Utilisateur> getUtilisateurFromMail(String mail) {
        Utilisateur unUtilisateur = utilisateurRepository.findUtilisateurByMail(mail);
        return Optional.ofNullable(unUtilisateur);
    }

    // L'Username utilisé est le mail pour la connexion
    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        Utilisateur unUtilisateur = null;
        unUtilisateur = utilisateurRepository.findUtilisateurByMail(mail);
        if (unUtilisateur != null) {
            return new User(unUtilisateur.getMail(), unUtilisateur.getMdp(),
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with mail: " + mail);
        }
    }

    public void register(Utilisateur util) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pass = encoder.encode(util.getMdp());
        util.setMdp(pass);
        utilisateurRepository.save(util);
    }

    public Optional<Utilisateur> getUtilisateurFromId(long id) {
        return utilisateurRepository.findById(id);
    }
}