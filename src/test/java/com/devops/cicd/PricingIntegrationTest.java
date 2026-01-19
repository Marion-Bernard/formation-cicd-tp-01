package com.devops.cicd;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PricingIntegrationTest {

    @Test
    void fullPricingFlow_withRealConfigFile() {
        // 1-Charger la config depuis le fichier réel
        PricingConfigLoader loader = new PricingConfigLoader();
        PricingConfig config = loader.load();

        // 2-Créer le service avec cette config
        PricingService service = new PricingService(config);

        // 3-Définir le scénario métier
        double amountHT = 100.0;  // montant hors taxe
        boolean isVip = true;     // client VIP

        // 4-Calculer le total
        double total = service.finalTotal(amountHT, isVip);

        // 5-Vérifier le résultat attendu

        assertEquals(108.0, total, 0.01, "Le total calculé doit correspondre au scénario complet");
    }
}
