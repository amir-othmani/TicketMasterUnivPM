package it.progettoesame.ticketmasterunivpm.controller;

import it.progettoesame.ticketmasterunivpm.service.TicketMasterService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

//Il controller gestisce le chiamate
@RestController
public class TicketMasterController {

    private TicketMasterService ticketMasterService = new TicketMasterService();

    public TicketMasterController() throws IOException {
        ticketMasterService.getJSONfromURL();
        ticketMasterService.getEventsFromFile();
    }

    //Spero non mi debba servire in futuro questa rotta
    /*
    @RequestMapping("/refresh")
    public ResponseEntity<Object> getNewData() throws IOException {
        return new ResponseEntity<>(ticketMasterService.getJSONfromURL(), HttpStatus.OK);
    }
     */

    //Rotta che restituisce gli eventi non filtrati
    @RequestMapping("/events")
    public ResponseEntity<Object> getRawEvents() {
        return new ResponseEntity<>(ticketMasterService.getNotFilteredEvents(), HttpStatus.OK);
    }
}
