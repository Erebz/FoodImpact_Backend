package com.codev.foodimpact.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app.external")
public class ConfigurationURLs {
    private String ipOpenFoodFacts = "https://fr.openfoodfacts.org/api/v0/product/";

    public String getIpOpenFoodFacts(){
        return this.ipOpenFoodFacts;
    }

    private String ipElectricityMap = "https://fr.openfoodfacts.org/api/v0/product/";

    public String getIpElectricityMap(){
        return this.ipElectricityMap;
    }
}

