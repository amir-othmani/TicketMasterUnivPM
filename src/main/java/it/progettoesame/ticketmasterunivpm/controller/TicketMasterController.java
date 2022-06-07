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
    //TO-DO: QUESTA ROTTA è DA MODIFICARE
    @RequestMapping("/events")
    public ResponseEntity<Object> getNotFilteredEvents(@RequestParam Map<String, String> selectedParam) {
        return new ResponseEntity<>(ticketMasterService.getEventsFromURL(selectedParam), HttpStatus.OK);
    }

    @RequestMapping("/events/filter")
    public ResponseEntity<Object> getFilteredEvents(@RequestParam Map<String, String> requestParam) {
        return new ResponseEntity<>(requestParam.getOrDefault("test", "nothing to see here"), HttpStatus.OK);
    }
}
