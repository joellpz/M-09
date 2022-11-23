package ThreadsAndThings.JocScheduler;

public class Jugador implements Runnable {
    private String nom;
    private int punts = 0;

    public Jugador(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public int getPunts() {
        return punts;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPunts(int punts) {
        this.punts = punts;
    }

    public int sumarPunts(int p) {
        punts += p;
        return punts;
    }

    @Override
    public void run() {
        int suma = (int) Math.floor(Math.random() * 25) + 1;
        sumarPunts(suma);
        System.out.println(nom + " ha sumat " + suma + " punts.");
    }

    @Override
    public String toString() {
        return "\n" + nom + " ha obtingut " + punts + " punts";
    }
}
