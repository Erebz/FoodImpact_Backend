package com.codev.foodimpact.controllers;

import com.codev.foodimpact.dto.DetailedProduitDTO;
import com.codev.foodimpact.dto.SimpleProduitDTO;
import com.codev.foodimpact.entities.Produit;
import com.codev.foodimpact.services.ProduitService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/produits")
public class ProduitController {

    @Value(value = "${api.open_food_facts}")
    private String apiOpenFoodFacts;

    @Value(value = "${api.eco2mix}")
    private String apiECO2mix;

    @Autowired
    private ProduitService produitService;

    private Random random = new Random();

    @GetMapping(value = "/list")
    public ResponseEntity<?> getAllProduits(){
        List<Produit> produits = produitService.getAll();
        if(produits != null){
            List<SimpleProduitDTO> produitDTOs = produits.stream().map(SimpleProduitDTO::new)
                    .collect(Collectors.toList());
            produitDTOs.sort(Comparator.comparing(SimpleProduitDTO::getNbFavs).reversed());
            return ResponseEntity.ok(produitDTOs);
        }else return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/random")
    public ResponseEntity<?> getRandom(){
        List<Produit> produits = produitService.getAll();
        if(produits != null){
            Produit produit = produits.get(random.nextInt(produits.size()));
            SimpleProduitDTO produitDTO = new SimpleProduitDTO(produit);
            return ResponseEntity.ok(produitDTO);
        }else return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/{codebarre}")
    public ResponseEntity<?> getInfoFromCodeBarre(@PathVariable Long codebarre){
        RestTemplate http = new RestTemplate();
        Gson g = new Gson();

        // Récupérer les informations du produit sur Open Food Facts
        String url = apiOpenFoodFacts + codebarre + ".json";
        String res = http.getForObject(url, String.class);
        JsonObject json = g.fromJson(res, JsonObject.class);

        if(json.get("status").getAsInt() == 1) {
            JsonObject produitJson = json.getAsJsonObject("product");
            Optional<Produit> _produitBDD = produitService.getByCodebarre(codebarre);
            Produit produit;

            // Charger ou sauvegarder le produit s'il n'existe pas
            if(_produitBDD.isEmpty()){
                produit = new Produit();
                produit.setCodebarre(codebarre);
                produit.setNom(produitJson.get("product_name_fr").getAsString());
                produit.setCategorie(produitJson.get("categories").getAsString());
                produitService.saveProduit(produit);
            }else produit = _produitBDD.get();

            // Récupérer les information émission CO2 sur eCO2mix
            url = apiECO2mix + "&q=date_heure>=\""+ getTodayDate() +"\"";
            res = http.getForObject(url, String.class);
            JsonObject co2Json = g.fromJson(res, JsonObject.class);

            DetailedProduitDTO produitDto = new DetailedProduitDTO(produit, produitJson, co2Json);
            return ResponseEntity.ok(produitDto);
        } else
            return ResponseEntity.notFound().build();
    }

    private String getTodayDate(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, -12);
        String date = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        String time = new SimpleDateFormat("HH:00:00").format(cal.getTime());
        return date + "T" + time + "Z";
    }
}
