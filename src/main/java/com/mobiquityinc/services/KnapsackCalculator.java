package com.mobiquityinc.services;

import com.mobiquityinc.exception.APIException;

public class KnapsackCalculator implements ItemCalculator {

    private Double maxWeight;
    private Double[] weights;
    private Double[] values;

    @Override
    public String getItems() throws APIException {
        StringBuilder builder = new StringBuilder();
        int n = values.length;
        int[] array = new int[n];
        initializeArrray(array);
        Double bestProfit = knapSack(maxWeight, weights, values, n,  array);
        if (bestProfit == 0.0)
            builder.append("-");
        else {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == 1)
                    builder.append(i+1 + ",");
            }
        }

        if (builder.charAt(builder.length() - 1) == ',')
            builder.deleteCharAt(builder.length() - 1);

        return builder.toString();
    }

    private Double knapSack(Double maxW, Double[] wt, Double[] val, int n, int[] array) {
        // Base Case

        if (n == 0 || maxW == 0)
            return 0.0;

        // If weight of the nth item is more than Knapsack capacity W, then
        // this item cannot be included in the optimal solution
        if (wt[n-1] > maxW)
        {

            return knapSack(maxW, wt, val, n-1, array);
        }
        // Return the maximum of two cases:
        // (1) nth item included
        // (2) not included
        else {

            int v1[]=new int[array.length];
            System.arraycopy(array, 0, v1, 0, v1.length);
            int v2[]=new int[array.length];
            System.arraycopy(array, 0, v2, 0, v2.length);
            v1[n-1]=1;

            Double ans1 = val[n-1] + knapSack(maxW-wt[n-1], wt, val, n-1,v1);
            Double ans2 = knapSack(maxW, wt, val, n-1,v2);
            if(ans1>ans2){
                System.arraycopy(v1, 0, array, 0, v1.length);
                return ans1;
            }
            else{
                System.arraycopy(v2, 0, array, 0, v2.length);
                return ans2;
            }
        }

       }


    private void initializeArrray(int array[]) {
        for (int i = 0; i < array.length; i++) {
            array[i] = 0;
        }
    }

    private Double max(Double a, Double b) { return (a > b)? a : b; }

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
