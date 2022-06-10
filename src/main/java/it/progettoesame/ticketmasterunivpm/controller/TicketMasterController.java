package it.progettoesame.ticketmasterunivpm.controller;


import it.progettoesame.ticketmasterunivpm.exceptions.MissingCountryException;
import it.progettoesame.ticketmasterunivpm.exceptions.NotSupportedParametersException;
import it.progettoesame.ticketmasterunivpm.service.TicketMasterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class TicketMasterController {

    @Autowired
    TicketMasterService ticketMasterService;

    @RequestMapping("/events")
    public ResponseEntity<Object> getSelectedEvents(@RequestParam Map<String, String> eventsParam) {
        try {
            if (ticketMasterService.areSupportedParam(eventsParam, ticketMasterService.getSupportedEventsParam())) {
                if (eventsParam.containsKey("country"))
                    return new ResponseEntity<>(ticketMasterService.getEvents(eventsParam), HttpStatus.OK);
                else
                    throw new MissingCountryException();
            }
            else
                throw new NotSupportedParametersException();
        }
        catch ( NotSupportedParametersException | MissingCountryException e ) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/stats")
    public ResponseEntity<Object> getStats(@RequestParam Map<String, String> statsParam) {
        try {
            if (ticketMasterService.areSupportedParam(statsParam, ticketMasterService.getSupportedStatsParam())) {
                if (statsParam.containsKey("country"))
                    return new ResponseEntity<>(ticketMasterService.getStats(statsParam), HttpStatus.OK);
                else
                    throw new MissingCountryException();
            }
            else
                throw new NotSupportedParametersException();
        }
        catch ( NotSupportedParametersException | MissingCountryException e ) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
