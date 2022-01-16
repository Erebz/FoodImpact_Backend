package com.codev.foodimpact.controllers;

import com.codev.foodimpact.entities.Produit;
import com.codev.foodimpact.util.ConfigurationURLs;
import com.codev.foodimpact.util.ProduitOFF;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/produits")
public class ProduitController {

    private final ConfigurationURLs urls;

    @Autowired
    ProduitController(ConfigurationURLs URLS){
        this.urls = URLS;
    }

    //@Autowired
    //private ProduitService produitService;

    @GetMapping(value = "/{codebarre}")
    public Produit getInfoFromCodeBarre(@PathVariable Long codebarre){
        RestTemplate http = new RestTemplate();
        String url = urls.getIpOpenFoodFacts() + codebarre + ".json";

        String res = http.getForObject(url, String.class);
        Gson g = new Gson();
        ProduitOFF produit_off = g.fromJson(res, ProduitOFF.class);
        Produit produit = new Produit();
        produit.setCodebarre(produit_off.getCode());
        produit.setNom(produit_off.getName());
        return produit;
    }

}
