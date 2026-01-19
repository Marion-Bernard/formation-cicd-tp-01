package com.devops.cicd;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PricingServiceTest {
    private final PricingConfig fakeConfig = new PricingConfig(0.20, 50.0); // TVA 20%, frais livraison si <50€
    private final PricingService service = new PricingService(fakeConfig);

    @Test
    void testApplyVat(){
        double result = service.applyVat(100); //montant HT 100€
        assertEquals(120.0, result); // Montant TTC 20€
    }

    @Test
    void testApplyVipDiscount(){
        double resultVip = service.applyVipDiscount(100, true);
        double resultNonVip = service.applyVipDiscount(100, false);
        assertEquals(90, resultVip); // 10€ de remise sur 100€
        assertEquals(100, resultNonVip); // pas de remise
    }

    @Test
    void testSHippingCost(){
        double resultFree = service.shippingCost(60);  // montant >= 50 → gratuit
        double resultPaid = service.shippingCost(40);  // montant < 50 → +4.99€
        assertEquals(0, resultFree, 0.01);
        assertEquals(4.99, resultPaid, 0.01);
    }

    @Test
    void testCalculateTotal() {
        double totalVip = service.finalTotal(40, true);   // HT=40, VIP
        double totalNonVip = service.finalTotal(40, false); // HT=40, Non VIP
        assertEquals(48.19, totalVip, 0.01);    // calcul final avec TVA+VIP+frais livraison
        assertEquals(52.99, totalNonVip, 0.01); // calcul final pour non VIP
    }

}
