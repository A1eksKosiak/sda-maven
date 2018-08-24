package week8;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ExperimentsTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test(expected = ArithmeticException.class)
    public void divide_ThrowsArithmeticException_IfDeivisionByZero() {
        // given
        int a = 10;
        int b = 0;

        // when
        int result = a / b;

        // then
        // should throw exception
    }

    @Test
    public void divide_ThrowsArithmeticException_IfDeivisionByZero2() {
        // given
        int a = 10;
        int b = 0;

        expectedException.expect(ArithmeticException.class);
        expectedException.expectMessage("/ by zero");
        // when
        int result = a / b;

        // then
        // should throw exception
    }


    // recommended approach
    @Test
    public void divide_ThrowsArithmeticException_IfDeivisionByZero3() {
        // given
        int a = 10;
        int b = 0;

        // when
        try {
            int result = a / b;

            // returns fail for the test, if previous line didn't throw an exception
            Assert.fail("No ArithmeticException was thrown");

        } catch (ArithmeticException e) {
            // then
            Assert.assertEquals("/ by zero", e.getMessage());
        }
    }
}
