package com.savko.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationValidation {

    private static final String REGEX_FIRST_SYMBOL = "[A-zА-я]";
    private static final String REGEX_WHITESPACE = "\\s";
    private static final Pattern FIRST_SYMBOL_PATTERN = Pattern.compile(REGEX_FIRST_SYMBOL);
    private static final Pattern WHITESPACE_PATTERN = Pattern.compile(REGEX_WHITESPACE);
    private static final short VALID_LOGIN_LENGTH = 4;

    public static boolean validateRegistrationForm(String name, String lastName, String login,
                                                   String password, String conformPassword) {

        boolean result = true;

        if(name.isEmpty() || lastName.isEmpty() || login.isEmpty() || password.isEmpty() || conformPassword.isEmpty() ) {
            result = false;
        }

        if(!checkField(name) || !checkField(lastName)) {
            result = false;
        }

        if (!login.isEmpty() && login.length() < VALID_LOGIN_LENGTH) {
            result = false;
        }

        if (!password.isEmpty() && !password.equals(conformPassword)) {
            result = false;
        }

        return result;

    }

    private static boolean checkField(String field) {
        boolean result = true;
        Matcher firstSymbolMatcher = FIRST_SYMBOL_PATTERN.matcher(field);
        Matcher whiteSpace = WHITESPACE_PATTERN.matcher(field);
        if(!firstSymbolMatcher.find() || whiteSpace.find()) {
            result = false;
        }
        return result;
    }

}
