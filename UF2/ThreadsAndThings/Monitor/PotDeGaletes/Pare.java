package UF2.ThreadsAndThings.Monitor.PotDeGaletes;

public class Pare extends Thread{
    private PotGaletes potGaletes;
    private String nom;

    public Pare(PotGaletes potGaletes, String nom) {
        this.potGaletes = potGaletes;
        this.nom = nom;
    }

    public void omplirPot(){
        System.out.println("*Omplint el POT");
        potGaletes.setNumGaletes(25);
    }

    @Override
    public void run() {
        while (true) {
            potGaletes.agafat();
            System.out.println(nom + " ha agafat el pot!");
            omplirPot();
            potGaletes.deixar();
            try {
                Thread.sleep((long) Math.random()*100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
