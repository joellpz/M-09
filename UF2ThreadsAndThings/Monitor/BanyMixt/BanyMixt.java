package UF2ThreadsAndThings.Monitor.BanyMixt;

public class BanyMixt {
    public String ocupat;
    private int cont;
    private final int max;
    boolean espera;

    public BanyMixt(int max) {
        this.ocupat = "none";
        this.cont = 0;
        this.max = max;
        espera = false;
    }

    public synchronized void entrarBany(String who) {
        do {
            espera = true;
            if (this.ocupat.equals(who) && cont < max) {
                cont++;
                espera = false;
            } else if (this.ocupat.equals("none")) {
                ocupat = who;
                cont++;
                System.out.println("CAMBIOOOOOOO a " + ocupat);
                notifyAll();
                espera = false;
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        } while (espera);
    }

    public synchronized void sortirBany() {
        if (cont == 1) {
            cont--;
            ocupat = "none";
        } else cont--;
        notifyAll();

    }

}
