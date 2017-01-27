package test.savko.util;

import com.savko.util.CostUtil;
import com.savko.util.UtilException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CostUtilTest {

    private int amountOfPlaces;
    private int amountOfDays;
    private int userDiscount;

    @Before
    public void doBefore() {
        amountOfPlaces = 3;
        amountOfDays = 4;
        userDiscount = 10;
    }

    @Test
    public void testCalculateCost() throws UtilException {
        Assert.assertEquals(162.0, CostUtil.calculateCost(amountOfPlaces, amountOfDays, userDiscount), 0);
    }

}
