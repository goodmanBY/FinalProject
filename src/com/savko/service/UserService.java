package com.savko.service;

import com.savko.dao.DaoException;
import com.savko.dao.UserDao;
import com.savko.entity.User;

import java.util.List;

public class UserService {

    public static UserService getInstance() {
        return StaticHolder.INSTANCE;
    }

    private static class StaticHolder {
        static final UserService INSTANCE = new UserService();
    }

    public void addUser(User user) throws ServiceException {
        try {
            UserDao.getInstance().addUser(user);
        } catch (DaoException e) {
            throw new ServiceException("Unable to add user.", e);
        }
    }

    public boolean checkUser(String login, String password) throws ServiceException {
        try {
            return UserDao.getInstance().checkUser(login, password);
        } catch (DaoException e) {
            throw new ServiceException("Unable to find such user.", e);
        }
    }

    public boolean checkUserLogin(String login) throws ServiceException {
        try {
            return UserDao.getInstance().checkUserLogin(login);
        } catch (DaoException e) {
            throw new ServiceException("Unable to find such user.", e);
        }
    }

    public User takeUser(String login) throws ServiceException {
        try {
            return UserDao.getInstance().takeUser(login);
        } catch (DaoException e) {
            throw new ServiceException("Unable to take such user from DB.", e);
        }
    }

    public User takeUser(int userId) throws ServiceException {
        try {
            return UserDao.getInstance().takeUser(userId);
        } catch (DaoException e) {
            throw new ServiceException("Unable to take such user from DB.", e);
        }
    }

    public List<User> takeAllUsers() throws ServiceException {
        try {
            return UserDao.getInstance().takeAllUsers();
        } catch (DaoException e) {
            throw new ServiceException("Unable to take all users.", e);
        }
    }

    public void blockUser(int userId) throws ServiceException {
        try {
            UserDao.getInstance().blockUser(userId);
        } catch (DaoException e) {
            throw new ServiceException("Unable to update table 'client'.", e);
        }
    }

    public void unblockUser(int userId) throws ServiceException {
        try {
            UserDao.getInstance().unblockUser(userId);
        } catch (DaoException e) {
            throw new ServiceException("Unable to update table 'client'.", e);
        }
    }

    public void addBlockDescription(int userId, String blockDescription) throws ServiceException {
        try {
            UserDao.getInstance().addBlockDescription(userId, blockDescription);
        } catch (DaoException e) {
            throw new ServiceException("Unable to update table 'ban_info'.", e);
        }
    }

    public String takeBlockDescription(int userId) throws ServiceException {
        try {
            return UserDao.getInstance().takeBlockDescription(userId);
        } catch (DaoException e) {
            throw new ServiceException("Unable to take data from 'ban_info' table.", e);
        }
    }

}
