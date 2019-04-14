package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;

public class Packer {

public static String pack(String filePath) throws APIException {
    GenericPacker packer = null;
    PackerInjector injector = new KnapsackInjector();

    packer = injector.getPacker(); //Dependency injection
    
    return packer.pack(filePath);
}

}
