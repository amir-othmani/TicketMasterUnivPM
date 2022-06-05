package it.progettoesame.ticketmasterunivpm.controller;

import it.progettoesame.ticketmasterunivpm.service.TicketMasterService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



//Il controller gestisce le chiamate
@RestController
public class TicketMasterController {

    final private TicketMasterService ticketMasterService = new TicketMasterService();

    public TicketMasterController() {
        ticketMasterService.getEventsFromURL();
    }

    //Rotta che restituisce gli eventi non filtrati
    @RequestMapping("/events")
    public ResponseEntity<Object> getRawEvents() {
        return new ResponseEntity<>(ticketMasterService.getNotFilteredEvents(), HttpStatus.OK);
    }
}
