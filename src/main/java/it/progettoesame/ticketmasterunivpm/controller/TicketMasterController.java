package it.progettoesame.ticketmasterunivpm.controller;

import it.progettoesame.ticketmasterunivpm.service.TicketMasterService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class TicketMasterController {

    TicketMasterService ticketMasterService = new TicketMasterService();

    public TicketMasterController() throws IOException {
        ticketMasterService.JSONtoObject();
    }

    @RequestMapping("/refresh")
    public ResponseEntity<Object> getNewData() throws IOException {
        return new ResponseEntity<>(ticketMasterService.getJSONfromURL(), HttpStatus.OK);
    }

    @RequestMapping("/events")
    public ResponseEntity<Object> getRawEvents() {
        return new ResponseEntity<>(ticketMasterService.getNotFilteredEvents(), HttpStatus.OK);
    }
}
