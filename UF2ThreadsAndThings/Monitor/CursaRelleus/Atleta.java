package UF2ThreadsAndThings.Monitor.CursaRelleus;

public class Atleta extends Thread {

    Testimoni testimoni;
    String name;
    boolean cansat;

    public Atleta(String name, Testimoni testimoni) {
        this.name = name;
        this.testimoni = testimoni;
        this.cansat = false;
    }

    public boolean isCansat() {
        return cansat;
    }

    @Override
    public void run() {
        testimoni.agafar();
        corre();
        testimoni.deixar();
    }

    public void corre() {
        try {
            System.out.println("***" + name + " se ha puesto a CORRER**");
            Thread.sleep((long) (Math.random() * 5000) + 2000);
            cansat = true;
            System.out.println("FIN "+  name);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
