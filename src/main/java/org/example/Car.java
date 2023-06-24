package org.example;        //il package è la cartella nella quale si trovano i file .java

public class Car
{
    private int id;             //Creiamo gli attributi della classe che ci viene assegnata nella traccia
    private String nome;        // Gli attributi li capiamo in base ai dati di esempio che vengono indicati nella traccia
    private double prezzo;
    private int quantita;

    public Car(int id, String nome, double prezzo, int quantita)    // Creiamo il costruttore
    {
        this.id = id;                                               // Su IntelliJ basta cliccare il tasto destro del mouse ->
        this.nome = nome;                                           // -> Generate -> Constructor
        this.prezzo = prezzo;                                       // Selezionare tutti gli attributi tenendo premuto Ctrl
        this.quantita = quantita;                                   // Premere OK e verrà generato in automatico il metodo
    }

    // Per i metodi set e get bisogna fare la stessa cosa dei costruttori, selezionando Getter and Setter al posto di Constructor
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
}
