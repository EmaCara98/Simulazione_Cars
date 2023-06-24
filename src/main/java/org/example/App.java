package org.example;        //il package è la cartella nella quale si trovano i file .java

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/****************************** COMANDI PER EFFETTUARE I TEST ******************************/
// Aprire: Windows Powershell
// Digitare il comando: telnet 127.0.0.1 1234
// Nella schermata nera digitare il comando: all
// Poi digitare il comando: all_sorted
// Poi digitare delle lettere a caso, deve mostrare "Comandi inesistente"


/****************************** CODICE ******************************/
public class App 
{
    static final int portNumber = 1234;             // Definiamo la porta

    public static void main( String[] args )
    {
        System.out.println("Server started!");

        ServerSocket serverSocket = null;           // Inizializziamo il Server TCP
        try {
            serverSocket = new ServerSocket(portNumber);    // Assegnamo la porta al server
        } catch (IOException e) {
            e.printStackTrace();
        }

        Socket clientSocket = null;                 // Inizializziamo il CLient TCP

        while(true)
        {
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }

            ClientHandler clientHandler = new ClientHandler(clientSocket);
            WareHouse.getInstance().add(clientHandler);     // Aggiungiamo i client che si connettono alla lista dei client

            Thread thread = new Thread(clientHandler);      // Creiamo un thread per il client così possono connettersi altri client contemporaneamente
            thread.start();
        }
    }
}
