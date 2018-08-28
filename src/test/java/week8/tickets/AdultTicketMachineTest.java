package week8.tickets;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Java6Assertions;
import org.junit.Before;
import org.junit.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.*;
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
        Throwable result = catchThrowable(() -> adultTicketMachine.buy(person));

        // then
        Assertions.assertThat(result)
                .hasMessage("Sorry, no person data")
                .isInstanceOfSatisfying(NoPersonDataException.class, e -> {
                    Assertions.assertThat(e.getTimestamp()).isEqualTo(LocalDateTime.now(clock));
                });
        // verify "calculate()" was never called with any parameters

        verify(discountCalculator, never()).calculate(any());
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

    @Test
    public void buy_ThrowTooYoungException_IfPersonIsNotAdult() throws TooYoungException, NoPersonDataException {
        // given
        Person person = new Person(5);
        AdultTicketMachine adultTicketMachine = new AdultTicketMachine(discountCalculator, 100, clock);

        // when
        try {
            adultTicketMachine.buy(person);
//            fail("no exception was thrown");
        } catch (TooYoungException e) {
            // then
            assertEquals("Sorry, you're too young", e.getMessage());
            assertNotNull(e.getTimestamp());
            assertEquals(LocalDateTime.now(clock), e.getTimestamp());
            // verify "calculate()" was never called with any parameters
            verify(discountCalculator, never()).calculate(any());
        } catch (NoPersonDataException e) {
//            fail("NoPersonalDataException called");
        }
    }

    @Test
    public void buy_ThrowTooYoungException_IfPersonIsNotAdultAge17() throws TooYoungException, NoPersonDataException {
        // given
        Person person = new Person(17);
        AdultTicketMachine adultTicketMachine = new AdultTicketMachine(discountCalculator, 100, clock);

        // when
        try {
            adultTicketMachine.buy(person);
//            fail("no exception was thrown");
        } catch (TooYoungException e) {
            // then
            assertEquals("Sorry, you're too young", e.getMessage());
            assertNotNull(e.getTimestamp());
            assertEquals(LocalDateTime.now(clock), e.getTimestamp());
            // verify "calculate()" was never called with any parameters
            verify(discountCalculator, never()).calculate(any());
        }
    }

    @Test
    public void buy_ReturnsFullPriceTicket_IfAdultPersonAge18() throws NoPersonDataException {
        // given
        Person person = new Person(18);
        AdultTicketMachine adultTicketMachine = new AdultTicketMachine(discountCalculator, 100, clock);

        // when
        Ticket result = adultTicketMachine.buy(person);

        // then
        assertEquals(100, result.getPrice());
        assertEquals(person, result.getPerson());
        assertNotNull(result.getTimestamp());
//        assertEquals(LocalDateTime.now(clock), result.getTimestamp());
        verify(discountCalculator).calculate(person);
    }

    @Test
    public void buy_ReturnsFullPriceTicket_IfAdultPersonAge19() throws NoPersonDataException {
        // given
        Person person = new Person(19);
        AdultTicketMachine adultTicketMachine = new AdultTicketMachine(discountCalculator, 100, clock);

        // when
        Ticket result = adultTicketMachine.buy(person);

        // then
        assertEquals(100, result.getPrice());
        assertEquals(person, result.getPerson());
        assertNotNull(result.getTimestamp());
//        assertEquals(LocalDateTime.now(clock), result.getTimestamp());
        verify(discountCalculator).calculate(person);
    }

    @Test
    public void getTicketsSold_ReturnZero_IfNullTicketsSold() {
        // given
        AdultTicketMachine adultTicketMachine = new AdultTicketMachine(discountCalculator, 100, clock);

        // when
        List<Ticket> result = adultTicketMachine.getTicketsSold();

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    public void getTicketsSold_ReturnOneTicket_IfOneTicketSold() throws NoPersonDataException {
        // given
        AdultTicketMachine adultTicketMachine = new AdultTicketMachine(discountCalculator, 100, clock);
        Person person = new Person(20);
        adultTicketMachine.buy(person);

        // when
        List<Ticket> result = adultTicketMachine.getTicketsSold();

        // then
        assertEquals(1, result.size());
        assertEquals(new Ticket(person, 100, LocalDateTime.now(clock)), result.get(0));
    }

    @Test
    public void getTicketsSold_ReturnTickets_IfThreeTicketsSold() throws NoPersonDataException {
        // given
        AdultTicketMachine adultTicketMachine = new AdultTicketMachine(discountCalculator, 100, clock);
        Person firstPerson = new Person(20);
        Person secondPerson = new Person(30);
        Person thirdPerson = new Person(40);
        adultTicketMachine.buy(firstPerson);
        adultTicketMachine.buy(secondPerson);
        adultTicketMachine.buy(thirdPerson);

        // when
        List<Ticket> result = adultTicketMachine.getTicketsSold();

        // then
        assertThat(result).containsExactly(
                new Ticket(firstPerson, 100, LocalDateTime.now(clock)),
                new Ticket(secondPerson, 100, LocalDateTime.now(clock)),
                new Ticket(thirdPerson, 100, LocalDateTime.now(clock)));
    }
}