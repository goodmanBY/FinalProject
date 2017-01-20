package com.savko.service;

import com.savko.dao.AdminDao;
import com.savko.dao.DaoException;

public class AdminService {

    public static AdminService getInstance() {
        return StaticHolder.INSTANCE;
    }

    public boolean checkAdmin(String login, String password) throws ServiceException {
        try {
            return AdminDao.getInstance().checkAdmin(login, password);
        } catch (DaoException e) {
            throw new ServiceException("Unable to check admin.", e);
        }
    }

    public void addAdmin(String login, String password) throws ServiceException {
        try {
            AdminDao.getInstance().addAdmin(login, password);
        } catch (DaoException e) {
            throw new ServiceException("Unable to add admin.", e);
        }
    }

    private static class StaticHolder {
        static final AdminService INSTANCE = new AdminService();
    }

}
