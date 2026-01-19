package com.devops.cicd;

import java.io.InputStream;
import java.util.Properties;

public class PricingConfigLoader {

    public PricingConfig load() {
        try {
            Properties props = new Properties();

            // Lire le fichier depuis resources
            InputStream input = getClass().getClassLoader().getResourceAsStream("app.properties");
            if (input == null) {
                throw new RuntimeException("Impossible de trouver app.properties");
            }

            props.load(input);

            // Récupérer les valeurs
            double vatRate = Double.parseDouble(required(props, "vatRate"));
            double freeShippingThreshold = Double.parseDouble(required(props, "freeShippingThreshold"));

            return new PricingConfig(vatRate, freeShippingThreshold);

        } catch (Exception e) {
            throw new RuntimeException("Erreur lors du chargement de la configuration", e);
        }
    }

    // Méthode utilitaire pour vérifier que la clé existe
    private String required(Properties props, String key) {
        String value = props.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Clé manquante dans app.properties: " + key);
        }
        return value;
    }
}