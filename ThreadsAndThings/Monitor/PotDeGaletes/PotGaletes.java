package ThreadsAndThings.Monitor.PotDeGaletes;

public class PotGaletes {
    private boolean ocupat;
    private int numGaletes;

    public PotGaletes() {
        this.ocupat = false;
    }

    public synchronized int getNumGaletes() {
        return numGaletes;
    }

    public synchronized void setNumGaletes(int numGaletes) {
        this.numGaletes = numGaletes;
    }

    public synchronized void agafat() {
        try {
            while (ocupat) wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ocupat = true;
        System.out.println("***Queden "+ numGaletes+ " galetes.");
        notifyAll();
    }

    public synchronized void deixar() {
        ocupat = false;
        notifyAll();
    }
}
