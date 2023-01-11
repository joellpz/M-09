package UF2ThreadsAndThings.Monitor.PeleaTelevisor;

public class MembreFamilia extends Thread{
    private Comandament comandament;
    private String nom;

    public MembreFamilia(Comandament comandament, String nom) {
        this.comandament = comandament;
        this.nom = nom;
    }

    @Override
    public void run() {
        for (;;){
            comandament.agafar();
            System.out.println(nom + " ha agafat el comandament!");
            try {
                Thread.sleep((long) (Math.random()*800)+200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            comandament.deixar();
            try {
                Thread.sleep((long) (Math.random()*800)+200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
