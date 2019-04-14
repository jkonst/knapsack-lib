package com.mobiquityinc.utils;

public class KnapsackCalculator implements ItemCalculator {

    public KnapsackCalculator(Double maxWeight, Double[] weights, Double[] values) {
        this.maxWeight = maxWeight;
        this.weights = weights;
        this.values = values;
    }

    private Double maxWeight;
    private Double[] weights;
    private Double[] values;

    @Override
    public String getItems() {
        //TODO
        return null;
    }

}
