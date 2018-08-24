package ee.sda.maven;

import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {

    @Test
    public void sum_Returns0_IfCalledWithNoArgs() {
        // given
        Calculator calculator = new Calculator();
        // when
        int sum = calculator.sum();
        // then
        Assert.assertEquals(0, sum);
    }

    @Test
    public void sum_Returns0_IfCalledWithNull() {
        // given
        Calculator calculator = new Calculator();
        String input = null;
        // when
        int sum = calculator.sum(input);
        // then
        Assert.assertEquals(0, sum);
    }

    @Test
    public void sum_Returns0_IfInputHasNoNumbers() {
        Calculator calculator = new Calculator();
        int sum = calculator.sum("asdfa asdgs qwer hsfd");
        Assert.assertEquals(0, sum);
    }

    @Test
    public void sum_ReturnsSameNumber_IfInputHasOnlyOneNumber() {
        Calculator calculator = new Calculator();
        int sum = calculator.sum("32");
        Assert.assertEquals(32, sum);
    }

    @Test
    public void sum_ReturnsCorrectSum_IfInputHasSumOfTwoNumbers() {
        Calculator calculator = new Calculator();
        int sum = calculator.sum("15+40");
        Assert.assertEquals(55, sum);
    }

    @Test
    public void sum_ReturnsCorrectSum_IfInputHasPartlyValidNumbers() {
        Calculator calculator = new Calculator();
        int sum = calculator.sum("15+4abc0");
        Assert.assertEquals(15, sum);
    }

    @Test
    public void sum_ReturnsCorrectSum_IfInputHasManyValidNumbers() {
        Calculator calculator = new Calculator();
        int sum = calculator.sum("15+1+2+3+4+5+6+7+8+9+10");
        Assert.assertEquals(70, sum);
    }

    @Test
    public void sum_ReturnsCorrectSum_IfInputHasNegativeNumbers() {
        Calculator calculator = new Calculator();
        int sum = calculator.sum("-100+-1");
        Assert.assertEquals(-101, sum);
    }

    @Test
    public void sumForBoolean() {
        Calculator calculator = new Calculator();
        boolean a = false;
        boolean b = false;
        boolean sum = calculator.sum(a, b);
    }

}
