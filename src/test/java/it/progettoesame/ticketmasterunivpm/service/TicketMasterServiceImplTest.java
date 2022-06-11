package it.progettoesame.ticketmasterunivpm.service;

import it.progettoesame.ticketmasterunivpm.filter.EventsFilter;
import it.progettoesame.ticketmasterunivpm.parser.EventsParser;
import it.progettoesame.ticketmasterunivpm.stats.EventsStats;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class TicketMasterServiceImplTest {

    private TicketMasterServiceImpl service = new TicketMasterServiceImpl();
    final private JSONObject events = new JSONObject();
    final private JSONObject allStats = new JSONObject();
    final private JSONArray statsArray = new JSONArray();
    final private String[] supportedEventsParam = {"countryCode", "city", "local_date", "segment", "genre", "subgenre"};
    final private String[] supportedCountries = {"AL", "AT", "BE", "BG", "CH", "CY", "CZ", "DE", "DK", "EE", "ES", "FO",
            "FI", "FR", "GB", "GR", "HR", "HU", "IE", "IS", "IT", "LT", "LU", "MC", "ME", "MT", "ND", "NL", "NO", "PL",
            "PT", "RO", "RS", "SE", "SK", "SI", "TR", "UA"};
    final private EventsParser eventsParser = new EventsParser();
    final private EventsFilter eventsFilter = new EventsFilter();
    final private EventsStats eventsStats = new EventsStats();
    private String currentCountry = " ";

    @BeforeAll
    void setUp() throws Exception {

    }

    @Test
    void areSupportedParam() {
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
        assertEquals(value, false);
    }

    @Test
    void isSupportedCountry() {
        String country = "LT";
        boolean value = false;
        for (String c: supportedCountries)
            if (c.equals(country))
                value = true;
        assertEquals(value, true);
    }

    @Test
    void getEvents() {
    }

    @Test
    void getStats() {
    }
}