package com.mobiquityinc.services;

public class KnapsackCalculator implements ItemCalculator {

    private Double maxWeight;
    private Double[] weights;
    private Double[] values;

    @Override
    public String getItems() {
        //TODO
        return null;
    }


    public Double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(Double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public Double[] getWeights() {
        return weights;
    }

    public void setWeights(Double[] weights) {
        this.weights = weights;
    }

    public Double[] getValues() {
        return values;
    }

    public void setValues(Double[] values) {
        this.values = values;
    }
}
