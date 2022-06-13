package it.progettoesame.ticketmasterunivpm.service;

import it.progettoesame.ticketmasterunivpm.exceptions.NotSupportedCountryException;
import it.progettoesame.ticketmasterunivpm.exceptions.NotSupportedParametersException;
import org.json.simple.JSONObject;
import java.util.HashMap;

/**
 * Interfaccia della classe di service
 *
 * @author amir-othmani
 */
public interface TicketMasterServiceInt {

    String[] getSupportedEventsParam();

    String[] getSupportedStatsParam();

    void areSupportedParam(HashMap<String, String> param, String[] supportedParam) throws NotSupportedParametersException;

    void isSupportedCountry(String country) throws NotSupportedCountryException;

    JSONObject getEvents(HashMap<String, String> selectedParam);

    JSONObject getStats(HashMap<String, String> selectedParam);
}
