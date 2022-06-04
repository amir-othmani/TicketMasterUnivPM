package it.progettoesame.ticketmasterunivpm.controller;

import it.progettoesame.ticketmasterunivpm.service.TicketMasterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TicketMasterController {

    TicketMasterService ticketMasterService = new TicketMasterService();

    public TicketMasterController() {
        ticketMasterService.JSONtoObject();
    }

    @RequestMapping("/refresh")
    public ResponseEntity<Object> getNewData() {
        return new ResponseEntity<>(ticketMasterService.getJSONfromURL(), HttpStatus.OK);
    }
}
