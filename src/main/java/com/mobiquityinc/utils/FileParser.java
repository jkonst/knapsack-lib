package com.mobiquityinc.utils;

import com.mobiquityinc.exception.APIException;

public interface FileParser {

    public void parse(String filePath) throws APIException;
}
