package it.progettoesame.ticketmasterunivpm.controller;

import it.progettoesame.ticketmasterunivpm.exceptions.NotSupportedCountryException;
import it.progettoesame.ticketmasterunivpm.exceptions.NotSupportedParametersException;
import it.progettoesame.ticketmasterunivpm.service.TicketMasterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;


@RestController
public class TicketMasterController {

    @Autowired
    TicketMasterServiceImpl ticketMasterServiceImpl;

    @RequestMapping("/events")
    public ResponseEntity<Object> getSelectedEvents(@RequestParam HashMap<String, String> eventsParam) {
        try {
            if (ticketMasterServiceImpl.areSupportedParam(eventsParam, ticketMasterServiceImpl.getSupportedEventsParam())) {
                if (eventsParam.containsKey("countryCode") && ticketMasterServiceImpl.isSupportedCountry(eventsParam.get("countryCode")))
                    return new ResponseEntity<>(ticketMasterServiceImpl.getEvents(eventsParam), HttpStatus.OK);
                else
                    throw new NotSupportedCountryException();
            }
            else
                throw new NotSupportedParametersException();
        }
        catch ( Exception e ) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/stats")
    public ResponseEntity<Object> getStats(@RequestParam HashMap<String, String> statsParam) {
        try {
            if (ticketMasterServiceImpl.areSupportedParam(statsParam, ticketMasterServiceImpl.getSupportedStatsParam())) {
                if (statsParam.containsKey("countryCode") && ticketMasterServiceImpl.isSupportedCountry(statsParam.get("countryCode")))
                    return new ResponseEntity<>(ticketMasterServiceImpl.getStats(statsParam), HttpStatus.OK);
                else
                    throw new NotSupportedCountryException();
            }
            else
                throw new NotSupportedParametersException();
        }
        catch ( Exception e ) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
