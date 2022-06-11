package it.progettoesame.ticketmasterunivpm.service;

import it.progettoesame.ticketmasterunivpm.model.Event;
import it.progettoesame.ticketmasterunivpm.stats.EventsStats;
import org.json.simple.JSONArray;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe che testa le funzionalità principali del service
 *
 * @author amir-othmani
 */
class TicketMasterServiceImplTest {

    final private TicketMasterServiceImpl service = new TicketMasterServiceImpl();
    final private String[] supportedEventsParam = {"countryCode", "city", "local_date", "segment", "genre", "subgenre"};
    final private String[] supportedCountries = {"AL", "AT", "BE", "BG", "CH", "CY", "CZ", "DE", "DK", "EE", "ES", "FO",
            "FI", "FR", "GB", "GR", "HR", "HU", "IE", "IS", "IT", "LT", "LU", "MC", "ME", "MT", "ND", "NL", "NO", "PL",
            "PT", "RO", "RS", "SE", "SK", "SI", "TR", "UA"};


    @Test
    void areSupportedParamTest() {
        HashMap<String, String> param = new HashMap<>();
        param.put("countryCode", "DE");
        param.put("city", "Hannover");
        param.put("invalidParam", "invalidValue");
        boolean value;
        int verify = 0;
        for (String p : supportedEventsParam) {
            if (param.containsKey(p))
                verify++;
        }
        if (verify == param.size())
            value = true;
        else
            value = false;
        assertFalse(value);
    }

    @Test
    void isSupportedCountryTest() {
        String country = "LT";
        boolean value = false;
        for (String c: supportedCountries)
            if (c.equals(country)) {
                value = true;
                break;
            }
        assertTrue(value);
    }

    @Test
    void getEventsTest() {
        HashMap<String, String> param = new HashMap<>();
        param.put("countryCode", "IT");
        assertTrue(service.getEvents(param).containsKey("events_not_found"));
    }

    @Test
    void getStats() {
        EventsStats eventsStats = new EventsStats();
        JSONArray stats = new JSONArray();
        ArrayList<Event> events1 = new ArrayList<>();
        ArrayList<Event> events2 = new ArrayList<>();
        HashMap<String, String> param1 = new HashMap<>();
        HashMap<String, String> param2 = new HashMap<>();
        param1.put("city", "Frankfurt am Main");
        param2.put("city", "Lemgo");
        events1.add(new Event(
                "Monster Jam 2023 - LOGEN",
                "Z698xZC2Z17uabs",
                "https://www.ticketmaster.de/event/monster-jam-2023-logen-tickets/349631?language=en-us",
                "Germany",
                "Frankfurt am Main",
                LocalDate.parse("2023-07-22"),
                "Sports",
                "Miscellaneous",
                "Miscellaneous"
        ));
        events2.add(new Event(
                "TBV Lemgo Lippe - Handball Sport Verein Hamburg",
                "Z698xZC2Z178-FN",
                "https://www.ticketmaster.de/event/tbv-lemgo-lippe-handball-sport-verein-hamburg-tickets/421223?language=en-us",
                "Germany",
                "Lemgo",
                LocalDate.parse("2022-06-12"),
                "Sports",
                "Handball",
                "Handball"
        ));
        events1.add(new Event(
                "KALEO",
                "Z698xZC2Z178-eo",
                "https://www.ticketmaster.de/event/kaleo-tickets/421091?language=en-us",
                "Germany",
                "Frankfurt am Main",
                LocalDate.parse("2022-06-13"),
                "Music",
                "Rock",
                "Pop"
        ));
        stats.add(eventsStats.statsPerWeek(events1, param1));
        stats.add(eventsStats.statsPerWeek(events2, param2));
        assertEquals(stats.size(), 2);
    }
}