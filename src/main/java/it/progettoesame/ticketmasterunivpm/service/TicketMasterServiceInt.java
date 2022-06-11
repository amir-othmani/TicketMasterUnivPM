package it.progettoesame.ticketmasterunivpm.service;

import org.json.simple.JSONObject;
import java.util.HashMap;

public interface TicketMasterServiceInt {

    String[] getSupportedEventsParam();

    String[] getSupportedStatsParam();

    boolean areSupportedParam(HashMap<String, String> param, String[] supportedParam);

    boolean isSupportedCountry(String country);

    JSONObject getEvents(HashMap<String, String> selectedParam);

    JSONObject getStats(HashMap<String, String> selectedParam);
}
