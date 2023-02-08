package UF2.ThreadsAndThings.Monitor.MesaRedonda;

public class Cobert {
    private boolean free;

    public Cobert() {
        this.free = true;
    }

    public synchronized void agafarCobert() {
        try {
            if (!free) wait();
            free = false;
            notifyAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void deixarCobert() {
        free = true;
        notifyAll();
    }
}
