package com.codev.foodimpact.repositories;

import com.codev.foodimpact.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    Utilisateur findUtilisateurByMail(String mail);
    Utilisateur findUtilisateurByMailAndMdp(String mail, String mdp);
}
