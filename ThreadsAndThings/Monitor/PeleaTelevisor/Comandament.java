package ThreadsAndThings.Monitor.PeleaTelevisor;

public class Comandament { // MONITOR
    private boolean lliure;

    public Comandament() {
        this.lliure = true;
    }

    public synchronized void agafar() {
        try {
            while (!lliure) wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        lliure = false;
        notifyAll();
    }

    public synchronized void deixar() {
        lliure = true;
        notifyAll();
    }
}
