package com.mobiquityinc.packer;

import com.mobiquityinc.utils.KnapsackCalculator;
import com.mobiquityinc.utils.KnapsackParser;

public class KnapsackInjector implements PackerInjector {

    @Override
    public GenericPacker getPacker() {
        return new PackerOrchestrator(new KnapsackParser(), new KnapsackCalculator());
    }
}
