package week8.tickets;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static week8.tickets.PersonStatus.DISABLED;
import static week8.tickets.PersonStatus.STUDENT;

public class DiscountCalculatorTest {
    private static DiscountCalculator discountCalculator;

    @BeforeClass
    public static void setUpBeforeClass() {
        discountCalculator = new DiscountCalculator();
    }


    @Test
    public void calculate_ThrowsIllegalArgumentException_IfPersonIsNull() {
        // given

        // when
        try {
            discountCalculator.calculate(null);
            fail("no exception was thrown");
        } catch (IllegalArgumentException e) {
            // then
            assertEquals("Given person is null", e.getMessage());
        }
    }

    @Test
    public void calculate_ThrowsIllegalArgumentException_IfPersonsAgeIsNegative() {
        // given
        Person person = new Person(-1);

        // when
        try {
            discountCalculator.calculate(person);
            fail("no exception was thrown");
        } catch (IllegalArgumentException e) {
            // then
            assertEquals("Negative age is not allowed: -1", e.getMessage());
        }
    }

    @Test
    public void calculate_ReturnsHundred_IfPersonsAgeEquals0() {
        // given
        Person person = new Person(0);

        // when
        int result = discountCalculator.calculate(person);

        // then
        assertEquals(100, result);
    }

    @Test
    public void calculate_ReturnsHundred_IfPersonsAgeEquals1() {
        // given
        Person person = new Person(1);

        // when
        int result = discountCalculator.calculate(person);

        // then
        assertEquals(100, result);
    }

    @Test
    public void calculate_ReturnsHundred_IfPersonsAgeEquals5() {
        // given
        Person person = new Person(5);

        // when
        int result = discountCalculator.calculate(person);

        // then
        assertEquals(100, result);
    }

    @Test
    public void calculate_ReturnsHundred_IfPersonsAgeEquals6() {
        // given
        Person person = new Person(6);

        // when
        int result = discountCalculator.calculate(person);

        // then
        assertEquals(100, result);
    }

    @Test
    public void calculate_ReturnsZero_IfPersonsAgeEquals7() {
        // given
        Person person = new Person(7);

        // when
        int result = discountCalculator.calculate(person);

        // then
        assertEquals(75, result);
    }

    @Test
    public void calculate_ReturnsZero_IfPersonsAgeEquals17() {
        // given
        Person person = new Person(17);

        // when
        int result = discountCalculator.calculate(person);

        // then
        assertEquals(75, result);
    }

    @Test
    public void calculate_ReturnsZero_IfPersonsAgeEquals18AndStatusStudent() {
        // given
        Person person = new Person(18, STUDENT);

        // when
        int result = discountCalculator.calculate(person);

        // then
        assertEquals(50, result);
    }

    @Test
    public void calculate_ReturnsZero_IfPersonsAgeEquals18AndStatusNotStudent() {
        // given
        Person person = new Person(18);

        // when
        int result = discountCalculator.calculate(person);

        // then
        assertEquals(0, result);
    }

    @Test
    public void calculate_Returns50_IfPersonsAgeEquals19AndStatusStudent() {
        // given
        Person person = new Person(19, STUDENT);

        // when
        int result = discountCalculator.calculate(person);

        // then
        assertEquals(50, result);
    }

    @Test
    public void calculate_ReturnsZero_IfPersonsAgeEquals19AndStatusNotStudent() {
        // given
        Person person = new Person(19);

        // when
        int result = discountCalculator.calculate(person);

        // then
        assertEquals(0, result);
    }

    @Test
    public void calculate_Returns50_IfPersonsAgeEquals25AndStatusStudent() {
        // given
        Person person = new Person(25, STUDENT);

        // when
        int result = discountCalculator.calculate(person);

        // then
        assertEquals(50, result);
    }

    @Test
    public void calculate_ReturnsZero_IfPersonsAgeEquals25AndStatusNotStudent() {
        // given
        Person person = new Person(25);

        // when
        int result = discountCalculator.calculate(person);

        // then
        assertEquals(0, result);
    }

    @Test
    public void calculate_Returns50_IfPersonsAgeEquals26AndStatusStudent() {
        // given
        Person person = new Person(26, STUDENT);

        // when
        int result = discountCalculator.calculate(person);

        // then
        assertEquals(50, result);
    }

    @Test
    public void calculate_ReturnsZero_IfPersonsAgeEquals26AndStatusNotStudent() {
        // given
        Person person = new Person(26);

        // when
        int result = discountCalculator.calculate(person);

        // then
        assertEquals(0, result);
    }

    @Test
    public void calculate_ReturnsZero_IfPersonsAgeEquals27() {
        // given
        Person person = new Person(27);

        // when
        int result = discountCalculator.calculate(person);

        // then
        assertEquals(0, result);
    }

    @Test
    public void calculate_ReturnsZero_IfPersonsAgeEquals69() {
        // given
        Person person = new Person(69);

        // when
        int result = discountCalculator.calculate(person);

        // then
        assertEquals(0, result);
    }

    @Test
    public void calculate_Returns90_IfPersonsAgeEquals70() {
        // given
        Person person = new Person(70);

        // when
        int result = discountCalculator.calculate(person);

        // then
        assertEquals(90, result);
    }

    @Test
    public void calculate_Returns90_IfPersonsAgeEquals71() {
        // given
        Person person = new Person(71);

        // when
        int result = discountCalculator.calculate(person);

        // then
        assertEquals(90, result);
    }

    @Test
    public void calculate_Returns100_IfPersonsAgeIs5AndStatusIsDisabled() {
        // given
        Person person = new Person(5, DISABLED);

        // when
        int result = discountCalculator.calculate(person);

        // then
        assertEquals(100, result);
    }

    @Test
    public void calculate_Returns90_IfPersonsAgeIs7AndStatusIsDisabled() {
        // given
        Person person = new Person(7, DISABLED);

        // when
        int result = discountCalculator.calculate(person);

        // then
        assertEquals(90, result);
    }

    @Test
    public void calculate_Returns90_IfPersonsAgeIs19AndStatusIsDisabled() {
        // given
        Person person = new Person(19, DISABLED);

        // when
        int result = discountCalculator.calculate(person);

        // then
        assertEquals(90, result);
    }

    @Test
    public void calculate_Returns90_IfPersonsAgeIs27AndStatusIsDisabled() {
        // given
        Person person = new Person(27, DISABLED);

        // when
        int result = discountCalculator.calculate(person);

        // then
        assertEquals(90, result);
    }

    @Test
    public void calculate_Returns90_IfPersonsAgeIs69AndStatusIsDisabled() {
        // given
        Person person = new Person(69, DISABLED);

        // when
        int result = discountCalculator.calculate(person);

        // then
        assertEquals(90, result);
    }

}