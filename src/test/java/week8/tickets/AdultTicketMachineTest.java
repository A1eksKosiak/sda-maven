package week8.tickets;

import org.junit.Before;
import org.junit.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

//@RunWith(MockitoJUnitRunner.class)
public class AdultTicketMachineTest {

    //    @Mock
    private DiscountCalculator discountCalculator;

    private Clock clock = Clock.fixed(Instant.parse("2018-08-27T10:00:00Z"), ZoneId.of("Europe/Tallinn"));

    @Before
    public void setUp() throws Exception {
        discountCalculator = spy(new DiscountCalculator());
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
            // verify "calculate()" was never called with any parameters
            verify(discountCalculator,never()).calculate(any());
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
        // verify "calculate()" was called with "person" parameter
        verify(discountCalculator).calculate(person);
    }
}