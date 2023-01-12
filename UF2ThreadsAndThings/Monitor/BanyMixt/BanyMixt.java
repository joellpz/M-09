package UF2ThreadsAndThings.Monitor.BanyMixt;

public class BanyMixt {
    public String ocupat;
    private int cont;
    private final int max;

    public BanyMixt(int max) {
        this.ocupat = "none";
        this.cont = 0;
        this.max = max;
    }

    public synchronized void entrarBany(String who) {
        System.out.println("ENTRAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAR "+who);
        do {
            if (this.ocupat.equals(who) && cont < max) {
                cont++;
                break;
            } else if (this.ocupat.equals("none")) {
                ocupat = who;
                cont++;
                System.out.println("CAMBIOOOOOOO a " + ocupat);
                notifyAll();
                break;
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        } while (true);
    }

    public synchronized void sortirBany() {
        if (cont == 1) {
            cont--;
            ocupat = "none";
        } else cont--;
        notifyAll();

    }

}
