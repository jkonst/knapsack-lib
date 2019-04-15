package com.mobiquityinc.utils;

import com.mobiquityinc.exception.APIException;

import java.util.List;

public class KnapsackParser implements FileParser {

    private List<Double> maxWeights;
    private List<Double[]> weights;
    private List<Double[]> values;

    @Override
    public void parse(String filePath) throws APIException {
        //TODO
    }


    public List<Double> getMaxWeights() {
        return maxWeights;
    }

    public void setMaxWeights(List<Double> maxWeights) {
        this.maxWeights = maxWeights;
    }

    public List<Double[]> getWeights() {
        return weights;
    }

    public void setWeights(List<Double[]> weights) {
        this.weights = weights;
    }

    public List<Double[]> getValues() {
        return values;
    }

    public void setValues(List<Double[]> values) {
        this.values = values;
    }
}
