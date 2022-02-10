package com.codev.foodimpact.dto;

import com.codev.foodimpact.entities.Produit;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class DetailedProduitDTO extends SimpleProduitDTO {
    private String name;
    private int agribalyse_score;
    private String image;
    private ProduitData produit_data;
    private EnergyData nucleaire_data;
    private EnergyData charbon_data;
    private EnergyData gaz_data;

    public String getName() { return name; }
    public int getAgribalyse_score() { return agribalyse_score; }
    public String getImage() { return image; }
    public ProduitData getProduit_data() { return produit_data; }
    public EnergyData getNucleaire_data() { return nucleaire_data; }
    public EnergyData getCharbon_data() { return charbon_data; }
    public EnergyData getGaz_data() { return gaz_data; }

    public DetailedProduitDTO(Produit produit, JsonObject produitJson, JsonObject co2Json) {
        super(produit);
        if(produitJson != null){
            image = produitJson.get("image_url").getAsString();
            JsonObject scores = produitJson.getAsJsonObject("ecoscore_data").getAsJsonObject("agribalyse");
            if(scores != null){
                name = scores.get("name_fr").getAsString();
                agribalyse_score = scores.get("score").getAsInt();
                produit_data = new ProduitData(scores);
            }
        }
        if(co2Json != null){
            JsonArray records = co2Json.getAsJsonArray("records");
            if(records.size() > 0){
                JsonObject record = records.get(0).getAsJsonObject().get("fields").getAsJsonObject();
                int conso_charbon = record.get("charbon").getAsInt();
                int conso_gaz = record.get("gaz").getAsInt();
                int conso_nucleaire = record.get("nucleaire").getAsInt();
                nucleaire_data = new EnergyData(conso_nucleaire, 12, produit_data);
                gaz_data = new EnergyData(conso_gaz, 490, produit_data);
                charbon_data = new EnergyData(conso_charbon, 820, produit_data);
            }
        }
    }
}

class ProduitData{
    private double co2_agriculture;
    private double co2_consumption;
    private double co2_distribution;
    private double co2_packaging;
    private double co2_processing;
    private double co2_total;

    public double getCo2_agriculture() { return co2_agriculture; }
    public double getCo2_consumption() { return co2_consumption; }
    public double getCo2_distribution() { return co2_distribution; }
    public double getCo2_packaging() { return co2_packaging; }
    public double getCo2_processing() { return co2_processing; }
    public double getCo2_total() { return co2_total; }

    public ProduitData(JsonObject scores){
        co2_agriculture = scores.get("co2_agriculture").getAsDouble();
        co2_consumption = scores.get("co2_consumption").getAsDouble();
        co2_distribution = scores.get("co2_distribution").getAsDouble();
        co2_packaging = scores.get("co2_packaging").getAsDouble();
        co2_processing = scores.get("co2_processing").getAsDouble();
        co2_total = scores.get("co2_total").getAsDouble();
    }
}

class EnergyData{
    private double kg_co2_par_heure;
    private long eq_1kg_produit_total;
    private long eq_1kg_produit_agriculture;
    private long eq_1kg_produit_consumption;
    private long eq_1kg_produit_distribution;
    private long eq_1kg_produit_packaging;
    private long eq_1kg_produit_processing;

    public EnergyData(int conso, int intens_carbone, ProduitData produit_data) {
        kg_co2_par_heure = (double) conso * intens_carbone;
        if(produit_data.getCo2_total() > 0)
            eq_1kg_produit_total = (long) (kg_co2_par_heure / produit_data.getCo2_total());
        if(produit_data.getCo2_agriculture() > 0)
           eq_1kg_produit_agriculture = (long) (kg_co2_par_heure / produit_data.getCo2_agriculture());
        if(produit_data.getCo2_consumption() > 0)
            eq_1kg_produit_consumption = (long) (kg_co2_par_heure / produit_data.getCo2_consumption());
        if(produit_data.getCo2_distribution() > 0)
            eq_1kg_produit_distribution = (long) (kg_co2_par_heure / produit_data.getCo2_distribution());
        if(produit_data.getCo2_packaging() > 0)
            eq_1kg_produit_packaging = (long) (kg_co2_par_heure / produit_data.getCo2_packaging());
        if(produit_data.getCo2_processing() > 0)
            eq_1kg_produit_processing = (long) (kg_co2_par_heure / produit_data.getCo2_processing());
    }

    public double getKg_co2_par_heure() { return kg_co2_par_heure; }
    public long getEq_1kg_produit_total() { return eq_1kg_produit_total; }
    public long getEq_1kg_produit_agriculture() { return eq_1kg_produit_agriculture; }
    public long getEq_1kg_produit_consumption() { return eq_1kg_produit_consumption; }
    public long getEq_1kg_produit_distribution() { return eq_1kg_produit_distribution; }
    public long getEq_1kg_produit_packaging() { return eq_1kg_produit_packaging; }
    public long getEq_1kg_produit_processing() { return eq_1kg_produit_processing; }
}
