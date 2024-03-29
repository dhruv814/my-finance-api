package com.finance.common.exception;

public class ResourceNotFoundException
        extends RuntimeException {

    public ResourceNotFoundException(String fieldName,
            String fieldTitle,
            String fieldInfo) {
        super(String.format("%s is not found with %s : %s", fieldName, fieldTitle, fieldInfo));
    }

    public ResourceNotFoundException(String msg) {
        super(String.format("%s", msg));
    }

}
