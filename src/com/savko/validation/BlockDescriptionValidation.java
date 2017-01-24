package com.savko.validation;

public class BlockDescriptionValidation {

    public static boolean isValidBlockDescription(String description) {

        boolean result = true;

        if(description == null) {
            result = false;
        }

        return result;

    }

}
