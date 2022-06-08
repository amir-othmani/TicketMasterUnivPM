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
    public ResponseEntity<Object> getNotFilteredEvents(@RequestParam Map<String, String> selectedParam) {
        if (ticketMasterService.areSupportedParam(selectedParam)) {
            if (selectedParam.containsKey("country")) {
                return new ResponseEntity<>(ticketMasterService.getEvents(selectedParam), HttpStatus.OK);
            }
            else
                return new ResponseEntity<>("Please insert a european country", HttpStatus.OK);
        }
        else    //Probabilmente è da trasformare in un'eccezione
            return new ResponseEntity<>("Not supported parameters", HttpStatus.BAD_REQUEST);
    }
}
