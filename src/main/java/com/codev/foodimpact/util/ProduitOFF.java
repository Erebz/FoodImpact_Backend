package com.codev.foodimpact.util;

/**
 * Produit provenant d'open food facts
 * Les noms de variables correspondent aux clés JSON reçues lors d'un appel à l'API O.F.F.
 */
public class ProduitOFF {
    private Long code;
    private ProduitData product;

    public long getCode() { return this.code; }
    public String getName(){ return this.product.abbreviated_product_name; }
    public String getCategories(){ return this.product.categories; }
    public double getCo2_agriculture() { return product.ecoscore_data.agribalyse.co2_agriculture; }
    public double getCo2_consumption() { return product.ecoscore_data.agribalyse.co2_consumption; }
    public double getCo2_distribution() { return product.ecoscore_data.agribalyse.co2_distribution; }
    public double getCo2_packaging() { return product.ecoscore_data.agribalyse.co2_packaging; }
    public double getCo2_processing() { return product.ecoscore_data.agribalyse.co2_processing; }
    public double getCo2_total() { return product.ecoscore_data.agribalyse.co2_total; }
    public Integer getScore() { return product.ecoscore_data.agribalyse.score; }

}

class ProduitData {
    public String categories;
    public String abbreviated_product_name;
    public String abbreviated_product_name_fr;
    public EcoscoreData ecoscore_data;
}

class EcoscoreData{
    public Agribalyse agribalyse;
}

class Agribalyse{
    public double co2_agriculture;
    public double co2_consumption;
    public double co2_distribution;
    public double co2_packaging;
    public double co2_processing;
    public double co2_total;
    public Integer score;
}
