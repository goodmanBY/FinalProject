package com.savko.service;


public class ServiceException extends Exception {

    public ServiceException(String exception) {
        super(exception);
    }

    public ServiceException(String message, Throwable rootCause) {
        super(message, rootCause);
    }

}

