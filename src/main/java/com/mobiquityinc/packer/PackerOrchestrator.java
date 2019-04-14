package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.utils.FileParser;
import com.mobiquityinc.utils.ItemCalculator;
import com.mobiquityinc.utils.KnapsackParser;

public class PackerOrchestrator implements GenericPacker{

    private FileParser parser;
    private ItemCalculator calculator;


    public PackerOrchestrator(FileParser parser, ItemCalculator calculator)  {
        this.parser = parser;
        this.calculator = calculator;
    }

    @Override
    public String pack(String filePath) throws APIException {
            this.parser.parse(filePath);
            if (this.parser instanceof KnapsackParser) {
                // 3. Get lists of weights-values from KnapsackParser
            }
        // 4. Iterate through max weights
            // 5. Instantiate a KnapsackCalculator object for each max weight
        // 6. Call getItems()
        // 7. Build String response
        // 8. End of iteration - Return response
        //TODO
        return null;
    }
}
