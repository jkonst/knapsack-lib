package com.mobiquityinc.services;

import com.mobiquityinc.exception.APIException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

public class KnapsackParser implements FileParser {

    private List<Double> maxWeights = new ArrayList<>();
    private List<Double[]> weights = new ArrayList<>();
    private List<Double[]> values = new ArrayList<>();

    @Override
    public void parse(String filePath) throws APIException {
        List<String> lines = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            stream.forEach(line -> lines.add(line));
        } catch (IOException ioe) {
            throw new APIException("Failed to open file: " + filePath, ioe);
        }
        try {
            for (String line : lines) {
                String weightStr = line.substring(0, line.indexOf(":")).trim();
                Double weight = weightStr.contains(".") ? Double.parseDouble(weightStr) : new Double(Integer.parseInt(weightStr));
                if (weight > 100) // constraint for max weight of package
                    throw new APIException("Max weight of package exceeds max limit of 100");
                maxWeights.add(weight);
                parsePairs(line.substring(line.indexOf(":") + 1).trim());
            }
        } catch (Exception e) {
            this.maxWeights.clear();
            if (e instanceof APIException)
                throw (APIException) e;
            throw new APIException("Failed to parse file: " + filePath, e);
        }

    }

    /**
     * This is a helper method to parse values and weights of each line containing the triplet: index, weight, price
     * @param lineOfPairs
     */
    private void parsePairs(String lineOfPairs) throws APIException{
        String[] triplets = lineOfPairs.split(" ");
        if (triplets.length > 15) // constraint for max number of items i.e. 15
            throw new APIException("Items number exceeds max limit of 15");

        List<Double> tripletWeights = new ArrayList<>();
        List<Double> tripletPrices = new ArrayList<>();
        try {
            for (String triplet : triplets) {
                String[] values = triplet.substring(1, triplet.length() - 1).split(",");
                Double weight = Double.parseDouble(values[1]);
                if (weight > 100.0)
                    throw new APIException("weight of an item exceeds max limit of 100");
                tripletWeights.add(weight);
                String price = values[2];
                NumberFormat numberFormat = new DecimalFormat("¤#.00", new DecimalFormatSymbols(Locale.GERMANY));
                Double formattedPrice = Double.parseDouble(numberFormat.parse(price).toString());
                if (formattedPrice > 100.0)
                    throw new APIException("price of an item exceeds max limit of 100");
                tripletPrices.add(formattedPrice);
            }
              Double[] addedWeights = new Double[tripletWeights.size()];
              addedWeights = tripletWeights.toArray(addedWeights);
              Double[] addedPrices = new Double[tripletPrices.size()];
              addedPrices = tripletPrices.toArray(addedPrices);
              this.weights.add(addedWeights);
              this.values.add(addedPrices);
        } catch (Exception e) {
            this.weights.clear();
            this.values.clear();
            if (e instanceof APIException)
                throw (APIException) e;
            throw new APIException("Failed to parse a specific weight or price", e);
        }
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
