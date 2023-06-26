package org.example;

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
    static final int portNumber = 1234;

    public static void main( String[] args )
    {
        System.out.println("Server started!");

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Socket clientSocket = null;

        while(true)
        {
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }

            ClientHandler clientHandler = new ClientHandler(clientSocket);
            WareHouse.getInstance().add(clientHandler);

            Thread thread = new Thread(clientHandler);
            thread.start();
        }
    }
}
