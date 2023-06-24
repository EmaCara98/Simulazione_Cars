package org.example;        //il package è la cartella nella quale si trovano i file .java

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

public class ClientHandler implements Runnable{

    private Socket clientSocket = null;         // Attributo del socket
    private InetAddress address;                // Attributo del address
    private int port;                           // Attributo della porta
    private PrintWriter out;                    // Attributo per la stampa del risultato

    public ClientHandler(Socket clientSocket) {     // Costruttore che accetta la connessione di ogni client
        this.clientSocket = clientSocket;

        //Indirizzo e porta del client che si connette
        address = clientSocket.getInetAddress();
        port = clientSocket.getPort();

        System.out.println("Connected: " + address + "with port: " + port);
    }

    Boolean readLoop(BufferedReader in,  PrintWriter out ){     // metodo che legge il comando del client

        String s = "";

        try {
            while ((s = in.readLine()) != null) {
                System.out.println(s);

                /********* COMANDI CLIENT *********/
                switch(s)
                {
                    case "all":
                        out.println(WareHouse.getInstance().all());
                        break;
                    case "all_sorted":
                        out.println(WareHouse.getInstance().all_sorted());
                        break;
                    case "more_expensive":
                        out.println(WareHouse.getInstance().more_expensive());
                        break;
                    default:
                        out.println("Comando inesistente");     // Risposta nel caso venga inserito un comando che non esiste
                }
            }

            System.out.println("Disconnected: " + address + "with port: " + port);  // Stampiamo messaggio quando un client si disconnette
            WareHouse.getInstance().remove(this);
            System.out.println("Now we have " + WareHouse.getInstance().nOfClients() + " connected client");

            return true;

        } catch (IOException e) {       // Stampiamo messaggio quando un client si disconnette chiudendo la finestra
            System.out.println("Forcing disconnection for: " + address + "with port: " + port);
        }

        return false;
    }

    void handle()       // Metodo che gestisce l'input e l'output del client
    {
        out = null;
        try {       // Definiamo l'oggetto per l'output
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {       // Definiamo l'oggetto per l'input
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            readLoop(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        handle();
    }

    /*
    void write(String s)
    {
        out.println(s);
        out.flush();
    }
    */

}
