# TicketMasterUnivPM
Questo programma consiste in un'applicazione SpringBoot che si occupa di richiamare gli eventi che avranno luogo in Europa gestiti dal sito di prenotazione biglietti [TicketMaster](https://www.ticketmaster.it). In particolare, attraverso l'Application Programming Interface (API), il programma restituirà gli eventi in base ai parametri inseriti dall'utente (che verranno riportati in seguito). Il programma può inoltre restituire delle statistiche riguardante il numero degli eventi che si verificano in ogni città e la loro distribuzione lungo il periodo settimanale.

## Indice
* Rotte disponibili
  * /events
  * /stats
* Eccezioni che il programma può restituire
  * quando il paese selezionato non è europeo
  * quando il paese selezionato non contiene eventi
  * quando il paese selezionato è incompatibile con il modello del programma
  * quando l'utente seleziona dei parametri non validi
  * quando non ci sono eventi che corripsondono ai parametri selezionati
* Credits

## Rotte disponibili
Per poter utilizzare il programma è sufficiente clonare la repository in una cartella locale ed eseguirlo tramite un IDE che sia in grado di leggere il linguaggio Java e che supporti SpringBoot insieme alle sue dipendenze.

### Rotta "/events"
Questa rotta permette di ricavare gli eventi di un determinato paese europeo (nota: è necessario scegliere un paese europeo per poter visualizzare gli eventi, altrimenti il programma lancerà un'eccezione).
