package UF2ThreadsAndThings.Monitor.CursaRelleus;

public class Testimoni {
    boolean lliure;

    public Testimoni() {
        this.lliure = true;
    }

    public synchronized void agafar() {
        try {
            while (!lliure) {
                    wait();
            }
            lliure = false;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void deixar(){
        lliure = true;
        notifyAll();
    }
}
