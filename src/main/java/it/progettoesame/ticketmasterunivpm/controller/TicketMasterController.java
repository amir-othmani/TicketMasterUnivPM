package it.progettoesame.ticketmasterunivpm.controller;

import it.progettoesame.ticketmasterunivpm.service.TicketMasterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


//Il controller gestisce le chiamate
@RestController
public class TicketMasterController {

    @Autowired
    TicketMasterService ticketMasterService;

    //Rotta che restituisce gli eventi
    @RequestMapping("/events")
    public ResponseEntity<Object> getSelectedEvents(@RequestParam Map<String, String> eventsParam) {
        if (ticketMasterService.areSupportedParam(eventsParam, ticketMasterService.getSupportedEventsParam())) {
            if (eventsParam.containsKey("country"))
                return new ResponseEntity<>(ticketMasterService.getEvents(eventsParam), HttpStatus.OK);
            else
                return new ResponseEntity<>("Please select a european country", HttpStatus.BAD_REQUEST);
        }
        else
            return new ResponseEntity<>("Not supported parameters", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping("/stats")
    public ResponseEntity<Object> getStats(@RequestParam Map<String, String> statsParam) {
        if (ticketMasterService.areSupportedParam(statsParam, ticketMasterService.getSupportedStatsParam())) {
            if (statsParam.containsKey("country"))
                return new ResponseEntity<>(ticketMasterService.getStats(statsParam), HttpStatus.OK);
            else
                return new ResponseEntity<>("Please select a european country", HttpStatus.BAD_REQUEST);
        }
        else
            return new ResponseEntity<>("Not supported parameters", HttpStatus.BAD_REQUEST);
    }
}
