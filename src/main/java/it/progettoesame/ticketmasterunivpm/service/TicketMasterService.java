package it.progettoesame.ticketmasterunivpm.service;

import it.progettoesame.ticketmasterunivpm.model.Event;
import it.progettoesame.ticketmasterunivpm.parser.EventParser;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;


public class TicketMasterService {

    private String url = "https://app.ticketmaster.com/discovery/v2/events.json?size=2&apikey=ytOGRTWK4lKDd4B9gvj8odbPaejuGh8V";
    private File response = new File("./resources/response.json");
    private EventParser p = new EventParser();

    public File getResponse() {
        return response;
    }

    public void setResponse(File response) {
        this.response = response;
    }

    //Metodo che converte in un file locale di tipo json il risultato della chiamata API
    public String getJSONfromURL() {
        try {
            InputStream input = new URL(url).openStream();
            JSONParser parser = new JSONParser();
            JSONObject result = (JSONObject) parser.parse(new InputStreamReader(input));
            FileWriter fileOut = new FileWriter(response);
            fileOut.write(result.toString());
            fileOut.close();
        }
        catch ( Exception e ) {
            e.printStackTrace();
            return "Failed to retrieve data from URL";
        }
        return "Local data refreshed successfully.";
    }

    //Metodo che estrae gli eventi seguendo la struttura imposta dal model
    public void getEventsFromFile() throws IOException {
        try {
            JSONParser parser = new JSONParser();
            Object obj = null;
            obj = parser.parse(new FileReader(response));
            p.parseEventsArray(obj);
        }
        catch ( ParseException e ) {
            e.printStackTrace();
        }
    }

    public ArrayList<Event> getNotFilteredEvents (){
            return p.getEvents();
    }
}
