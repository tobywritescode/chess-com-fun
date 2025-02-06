package toby.nichol.chess.com.api.HelperTests;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import toby.nichol.chess.com.api.service.helper.Helper;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class HelperTests {


    @Test
    public void calculateRatioMethodShouldCalculateCorrectRatioForGivenNumbers(){
        BigDecimal expected = BigDecimal.valueOf(1.00).setScale(2, RoundingMode.HALF_UP);
        BigDecimal actual = Helper.calculateRatio(100, 100);
        BigDecimal expected1 = BigDecimal.valueOf(1.13).setScale(2, RoundingMode.HALF_UP);
        BigDecimal actual1 = Helper.calculateRatio(219, 194);
        BigDecimal expected2 = BigDecimal.valueOf(0.50).setScale(2, RoundingMode.HALF_UP);
        BigDecimal actual2 = Helper.calculateRatio(50, 100);

        assertEquals(expected, actual);
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }

    @Test
    public void calculateRatioMethodShouldThrowErrorIfNumbersAreZero(){
        assertThrows(RuntimeException.class, () -> {
            Helper.calculateRatio(50, 0);
        });
        assertThrows(RuntimeException.class, () -> {
            Helper.calculateRatio(0, 50);
        });
    }
}
