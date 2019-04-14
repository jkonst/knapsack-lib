package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;

public interface GenericPacker {

    public String pack(String filePath) throws APIException;

}
