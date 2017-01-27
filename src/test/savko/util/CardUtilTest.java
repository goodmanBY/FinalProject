package test.savko.util;

import com.savko.util.CardUtil;
import com.savko.util.CostUtil;
import com.savko.util.UtilException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CardUtilTest {

    private String cardNumber;

    @Before
    public void doBefore() {
        cardNumber = "2203487659423475";
    }

    @Test
    public void testGetFourLastDigits() throws UtilException {
        int fourLastDigits = 3475;
        Assert.assertTrue(fourLastDigits == CardUtil.getFourLastDigits(cardNumber));
    }

}
