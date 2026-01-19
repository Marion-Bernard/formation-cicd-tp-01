package com.devops.cicd;

public class PricingService {
    private final PricingConfig config;

    public PricingService(PricingConfig config) {
        this.config = config;
    }

    public double applyVat(double amountExclVat) {
        return amountExclVat * (1 + config.getVatRate());
    }

    public double applyVipDiscount(double amount, boolean vip) {
        if (vip) {
            amount = amount * 0.9;
        };
        return amount;
    }

    public double shippingCost(double amount) {
        if (amount > config.getFreeShippingThreshold() ){
            return 0;
        }
        return 4.99;
    }

    /**
     * - TVA appliquée d'abord : HT -> TTC
     * - remise VIP appliquée sur TTC
     * - frais de livraison ajoutés ensuite (calculés sur TTC)
     */
    public double finalTotal(double amountExclVat, boolean vip) {
        double finalTotal = applyVat(amountExclVat);
        finalTotal  = applyVipDiscount(finalTotal, vip);
        double shippingCost = shippingCost(finalTotal);
        return finalTotal + shippingCost;
    }
}
