package com.savko.service;

import com.savko.dao.AdminDao;
import com.savko.dao.DaoException;

public class AdminService {

    public static AdminService getInstance() {
        return StaticHolder.INSTANCE;
    }

    private static class StaticHolder {
        static final AdminService INSTANCE = new AdminService();
    }

    public boolean checkAdmin(String login, String password) throws ServiceException {
        try {
            return AdminDao.getInstance().checkAdmin(login, password);
        } catch (DaoException e) {
            throw new ServiceException("Unable to check admin.", e);
        }
    }

}
