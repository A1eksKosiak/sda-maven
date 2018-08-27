package week8.tickets;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AdultTicketMachineTest {

    private DiscountCalculator discountCalculator;

    private Clock clock = Clock.fixed(Instant.parse("2018-08-27T10:00:00Z"), ZoneId.of("Europe/Tallinn"));

    @Before
    public void setUp() throws Exception {
        discountCalculator = mock(DiscountCalculator.class);
    }

    @Test
    public void buy_ThrowsNoPersonDataException_IfPersonIsNull() {
        // given
        Person person = null;
        AdultTicketMachine adultTicketMachine = new AdultTicketMachine(discountCalculator, 100, clock);

        // when
        try {
            adultTicketMachine.buy(person);
            fail("no exception was thrown");
        } catch (NoPersonDataException e) {
            // then
            assertEquals("Sorry, no person data", e.getMessage());
            assertNotNull(e.getTimestamp());
            assertEquals(LocalDateTime.now(clock), e.getTimestamp());
        }
    }

    @Test
    public void buy_ReturnsFullPriceTicket_IfSubsidisedPersonAndNoDiscountCalculator() throws NoPersonDataException {
        // given
        Person person = new Person(50, PersonStatus.DISABLED);
        DiscountCalculator discountCalculator = null;
        AdultTicketMachine adultTicketMachine = new AdultTicketMachine(discountCalculator, 100, clock);

        // when
        Ticket result = adultTicketMachine.buy(person);

        // then
        assertEquals(100, result.getPrice());
        assertEquals(person, result.getPerson());
        assertNotNull(result.getTimestamp());
//        assertEquals(LocalDateTime.now(clock), result.getTimestamp());
    }

    @Test
    public void buy_ReturnsFullPriceTicket_IfSubsidisedPersonAndDiscountCalculator() throws NoPersonDataException {
        // given
        Person person = new Person(50, PersonStatus.DISABLED);
        // calling "when" from Mockito and mocking the "calculate()" for previously mocked object "discountCalculator"
        when(discountCalculator.calculate(person)).thenReturn(90);
        AdultTicketMachine adultTicketMachine = new AdultTicketMachine(discountCalculator, 100, clock);

        // when
        Ticket result = adultTicketMachine.buy(person);

        // then
        assertEquals(10, result.getPrice());
        assertEquals(person, result.getPerson());
        assertNotNull(result.getTimestamp());
//        assertEquals(LocalDateTime.now(clock), result.getTimestamp());
    }
}