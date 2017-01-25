package com.savko.service;

import com.savko.dao.DaoException;
import com.savko.dao.SettingDao;
import com.savko.entity.Discount;

import java.util.List;

public class SettingService {

    public static SettingService getInstance() {
        return SettingService.StaticHolder.INSTANCE;
    }

    public int takeRoomCost() throws ServiceException {
        try {
            return SettingDao.getInstance().takeRoomCost();
        } catch (DaoException e) {
            throw new ServiceException("Unable to take cost of room.", e);
        }
    }

    public void changeRoomCost(int roomCost) throws ServiceException {
        try {
            SettingDao.getInstance().changeRoomCost(roomCost);
        } catch (DaoException e) {
            throw new ServiceException("Unable to change cost of room.", e);
        }
    }

    public List<Discount> takeAllDiscounts() throws ServiceException {
        try {
            return SettingDao.getInstance().takeAllDiscounts();
        } catch (DaoException e) {
            throw new ServiceException("Unable take all discounts.", e);
        }
    }

    public void changeDiscountById(int discount, int discountId) throws ServiceException  {
        try {
            SettingDao.getInstance().changeDiscountById(discount, discountId);
        } catch (DaoException e) {
            throw new ServiceException("Unable to change discount value.", e);
        }
    }

    public Discount takeDiscountById(int discountId) throws ServiceException {
        try {
            return SettingDao.getInstance().takeDiscountById(discountId);
        } catch (DaoException e) {
            throw new ServiceException("Unable to take discount by ID.", e);
        }
    }

    private static class StaticHolder {
        static final SettingService INSTANCE = new SettingService();
    }

}
