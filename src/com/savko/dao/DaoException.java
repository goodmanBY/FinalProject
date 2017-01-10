package com.savko.dao;

public class DaoException extends Exception {

    public DaoException(String exception) {
        super(exception);
    }

    public DaoException(String message, Throwable rootCause) {
        super(message, rootCause);
    }

}
