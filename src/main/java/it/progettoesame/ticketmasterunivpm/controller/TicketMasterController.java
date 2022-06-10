package it.progettoesame.ticketmasterunivpm.controller;


import it.progettoesame.ticketmasterunivpm.exceptions.NotSupportedCountryException;
import it.progettoesame.ticketmasterunivpm.exceptions.NotSupportedParametersException;
import it.progettoesame.ticketmasterunivpm.service.TicketMasterService;

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
    TicketMasterService ticketMasterService;

    @RequestMapping("/events")
    public ResponseEntity<Object> getSelectedEvents(@RequestParam HashMap<String, String> eventsParam) {
        try {
            if (ticketMasterService.areSupportedParam(eventsParam, ticketMasterService.getSupportedEventsParam())) {
                if (eventsParam.containsKey("countryCode") && ticketMasterService.isSupportedCountry(eventsParam.get("countryCode")))
                    return new ResponseEntity<>(ticketMasterService.getEvents(eventsParam), HttpStatus.OK);
                else
                    throw new NotSupportedCountryException();
            }
            else
                throw new NotSupportedParametersException();
        }
        catch ( NotSupportedParametersException | NotSupportedCountryException e ) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/stats")
    public ResponseEntity<Object> getStats(@RequestParam HashMap<String, String> statsParam) {
        try {
            if (ticketMasterService.areSupportedParam(statsParam, ticketMasterService.getSupportedStatsParam())) {
                if (statsParam.containsKey("countryCode") && ticketMasterService.isSupportedCountry(statsParam.get("countryCode")))
                    return new ResponseEntity<>(ticketMasterService.getStats(statsParam), HttpStatus.OK);
                else
                    throw new NotSupportedCountryException();
            }
            else
                throw new NotSupportedParametersException();
        }
        catch ( NotSupportedParametersException | NotSupportedCountryException e ) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
