package it.progettoesame.ticketmasterunivpm.controller;

import it.progettoesame.ticketmasterunivpm.service.TicketMasterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


//Il controller gestisce le chiamate
@RestController
public class TicketMasterController {

    @Autowired
    TicketMasterService ticketMasterService;

    //Rotta che restituisce gli eventi non filtrati
    @RequestMapping("/events")
    public ResponseEntity<Object> getNotFilteredEvents(@RequestParam(name = "size", defaultValue = "1") String size) {
        return new ResponseEntity<>(ticketMasterService.getEventsFromURL(size), HttpStatus.OK);
    }
}
