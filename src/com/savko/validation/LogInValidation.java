package com.savko.validation;

public class LogInValidation {

    public static boolean validateLoginAndPassword(String login, String password) {

        boolean result = true;

        if(login.isEmpty() || password.isEmpty()) {
            result = false;
        }

        return result;
    }

}
