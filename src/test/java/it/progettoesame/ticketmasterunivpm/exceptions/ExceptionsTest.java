package it.progettoesame.ticketmasterunivpm.exceptions;


import it.progettoesame.ticketmasterunivpm.service.TicketMasterServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Questa classe testa le eccezioni previste dal programma
 */
public class ExceptionsTest {

     private TicketMasterServiceImpl service;
     private HashMap<String, String> param;

     @BeforeEach
     void setUp() {
         service = new TicketMasterServiceImpl();
         param = new HashMap<>();
     }

    @Test
    void EventParseExcpetionTest() {
        param.put("countryCode", "CH");
        EventParseExcpetion e = new EventParseExcpetion();
        assertEquals(service.getEvents(param).get("events_not_found"), e.getMessage());
    }

    @Test
    void EventsNotFoundExceptionTest() {
         param.put("countryCode", "IT");
         EventsNotFoundException e = new EventsNotFoundException();
         assertEquals(service.getEvents(param).get("events_not_found"), e.getMessage());
    }

    @Test
    void FilterMismatchExceptionTest() {
         param.put("countryCode", "DE");
         param.put("city", "Paris");
         FilterMismatchException e = new FilterMismatchException();
         assertEquals(service.getEvents(param).get("filtered_events_not_found"), e.getMessage());
    }

    @Test
    void NotSupportedCountryExceptionTest() {
        String countryCode = "US";
        Exception e = assertThrows(NotSupportedCountryException.class, () -> service.isSupportedCountry(countryCode));
        assertEquals("Please insert a european (and valid) country-code", e.getMessage());
    }

    @Test
    void NotSupportedParametersException() {
         param.put("invalidParameter", "invalidValue");
         Exception e = assertThrows(NotSupportedParametersException.class, () -> service.areSupportedParam(param, service.getSupportedStatsParam()));
         assertEquals("Not supported parameters", e.getMessage());
    }
}
