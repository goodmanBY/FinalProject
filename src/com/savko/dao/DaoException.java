package com.savko.dao;


import javax.servlet.ServletException;

public class DaoException extends ServletException {

    public DaoException(String exception){
        super(exception);
    }

}
