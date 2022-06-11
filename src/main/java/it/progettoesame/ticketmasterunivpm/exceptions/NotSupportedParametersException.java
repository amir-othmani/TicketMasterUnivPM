package it.progettoesame.ticketmasterunivpm.exceptions;

/**
 * Questa eccezione viene lanciata quando i parametri inseriti dall'utente non sono validi
 *
 * @author amir-othmani
 */
public class NotSupportedParametersException extends Exception {

    public NotSupportedParametersException() {
        super("Not supported parameters");
    }
}
