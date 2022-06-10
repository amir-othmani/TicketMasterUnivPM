package it.progettoesame.ticketmasterunivpm.exceptions;

public class NotSupportedCountryException extends Exception {

    public NotSupportedCountryException() {
        super("Please insert a european (and valid) country-code");
    }
}
