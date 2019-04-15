package com.mobiquityinc.services;

import com.mobiquityinc.exception.APIException;

public interface FileParser {

    public void parse(String filePath) throws APIException;
}
