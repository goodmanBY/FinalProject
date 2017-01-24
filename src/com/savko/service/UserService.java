package com.savko.service;

import com.savko.dao.DaoException;
import com.savko.dao.UserDao;
import com.savko.entity.User;

import java.util.List;

public class UserService {

    public static UserService getInstance() {
        return StaticHolder.INSTANCE;
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
            throw new ServiceException("Unable to check user.", e);
        }
    }

    public boolean checkUserLogin(String login) throws ServiceException {
        try {
            return UserDao.getInstance().checkUserLogin(login);
        } catch (DaoException e) {
            throw new ServiceException("Unable to check user.", e);
        }
    }

    public User takeUser(String login) throws ServiceException {
        try {
            return UserDao.getInstance().takeUser(login);
        } catch (DaoException e) {
            throw new ServiceException("Unable to take user.", e);
        }
    }

    public User takeUser(int userId) throws ServiceException {
        try {
            return UserDao.getInstance().takeUser(userId);
        } catch (DaoException e) {
            throw new ServiceException("Unable to take user.", e);
        }
    }

    public User takeUserByRequestId(int requestId) throws ServiceException {
        try {
            return UserDao.getInstance().takeUserByRequestId(requestId);
        } catch (DaoException e) {
            throw new ServiceException("Unable to take user.", e);
        }
    }

    public List<User> takeAllUsers() throws ServiceException {
        try {
            return UserDao.getInstance().takeAllUsers();
        } catch (DaoException e) {
            throw new ServiceException("Unable to take all users.", e);
        }
    }

    public void blockUser(int userId, String blockDescription) throws ServiceException {
        try {
            UserDao.getInstance().blockUser(userId);
            UserService.getInstance().addBlockDescription(userId, blockDescription);
        } catch (DaoException e) {
            throw new ServiceException("Unable to block user.", e);
        }
    }

    public void unblockUser(int userId) throws ServiceException {
        try {
            UserDao.getInstance().unblockUser(userId);
        } catch (DaoException e) {
            throw new ServiceException("Unable to unblock user.", e);
        }
    }

    public void addBlockDescription(int userId, String blockDescription) throws ServiceException {
        try {
            UserDao.getInstance().addBlockDescription(userId, blockDescription);
        } catch (DaoException e) {
            throw new ServiceException("Unable to add block description.", e);
        }
    }

    public String takeBlockDescription(int userId) throws ServiceException {
        try {
            return UserDao.getInstance().takeBlockDescription(userId);
        } catch (DaoException e) {
            throw new ServiceException("Unable to take block description.", e);
        }
    }

    public void changeUserDiscountValue(int discountValue, int userId) throws ServiceException {
        try {
            UserDao.getInstance().changeUserDiscountValue(discountValue, userId);
        } catch (DaoException e) {
            throw new ServiceException("Unable to change user discount value.", e);
        }
    }

    public int takeDiscountValueByUserId(int userId) throws ServiceException {
        try {
            return UserDao.getInstance().takeDiscountValueByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException("Unable to take discount value by user ID.", e);
        }
    }

    private static class StaticHolder {
        static final UserService INSTANCE = new UserService();
    }

}
