package it.progettoesame.ticketmasterunivpm.controller;

import it.progettoesame.ticketmasterunivpm.service.TicketMasterService;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

@RestController
public class TicketMasterController {

    TicketMasterService ticketMasterService = new TicketMasterService();

    public TicketMasterController()
            throws FileNotFoundException, MalformedURLException, IOException {
        ticketMasterService.JSONtoObject();
    }

    @RequestMapping("/refresh")
    public ResponseEntity<Object> getNewData() throws IOException, ParseException {
        return new ResponseEntity<>(ticketMasterService.getJSONfromURL(), HttpStatus.OK);
    }
}
