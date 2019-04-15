package com.mobiquityinc.packer;

import com.mobiquityinc.services.KnapsackCalculator;
import com.mobiquityinc.services.KnapsackParser;

public class KnapsackInjector implements PackerInjector {

    @Override
    public GenericPacker getPacker() {
        return new PackerOrchestrator(new KnapsackParser(), new KnapsackCalculator());
    }
}
