package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.services.FileParser;
import com.mobiquityinc.services.ItemCalculator;
import com.mobiquityinc.services.KnapsackCalculator;
import com.mobiquityinc.services.KnapsackParser;

import java.util.List;

public class PackerOrchestrator implements GenericPacker{

    //2 generic dependencies
    private FileParser parser;
    private ItemCalculator calculator;


    public PackerOrchestrator(FileParser parser, ItemCalculator calculator)  {
        this.parser = parser;
        this.calculator = calculator;
    }

    @Override
    public String pack(String filePath) throws APIException {
        StringBuilder stringBuilder = new StringBuilder();
        this.parser.parse(filePath);
        if (this.parser instanceof KnapsackParser && this.calculator instanceof KnapsackCalculator) {
            // 1. Get lists of weights-values from KnapsackParser
            // 2. Iterate through max weights
            // 3. Call KnapsackCalculator getItems()
            // 4. Build String response
            // 5. End of iteration - Return response
            List<Double> maxWeights = ((KnapsackParser) this.parser).getMaxWeights();
            List<Double[]> itemWeights = ((KnapsackParser) this.parser).getWeights();
            List<Double[]> itemValues = ((KnapsackParser) this.parser).getValues();
            for (int i = 0; i < maxWeights.size() ; i++) {
                Double[] weights = itemWeights.get(i);
                Double[] values = itemValues.get(i);
                Double maxWeight = maxWeights.get(i);
                ((KnapsackCalculator) this.calculator).setMaxWeight(maxWeight);
                ((KnapsackCalculator) this.calculator).setWeights(weights);
                ((KnapsackCalculator) this.calculator).setValues(values);
                stringBuilder.append(this.calculator.getItems());
                stringBuilder.append(System.getProperty("line.separator"));
            }
        }
        return stringBuilder.toString();
    }
}
