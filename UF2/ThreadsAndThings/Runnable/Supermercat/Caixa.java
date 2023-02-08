package UF2.ThreadsAndThings.Runnable.Supermercat;

import java.util.List;

public class Caixa implements Runnable {

    private String nom;
    private List<Client> client;
    private float preu;

    public Caixa(String nom) {
        this.nom = nom;
    }

    public void setClients(List<Client> c) {
        this.client = c;
    }

    public void sumaCarro(Client c){
        preu = 0;
        for (float f: c.getCarret()) {
            System.out.println(f);
            preu += f;
        }
    }

    @Override
    public void run() {
        for (Client c: client) {
            sumaCarro(c);
            System.out.println("El Client " + c.getNom() + " ha pagat "+ preu+"€ a la Caixa  "+nom+"\n");
        }

    }
}
