package UF2.ThreadsAndThings.Monitor.MesaRedonda;

import static UF2.ThreadsAndThings.Monitor.MesaRedonda.Main.lock;

public class Filosof extends Thread {
    private String name;
    private Cobert c1;
    private Cobert c2;

    public Filosof(String name, Cobert c1, Cobert c2) {
        this.name = name;
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep((long) ((Math.random() * 300) + 200));
                //synchronized (dead) { // with an Object in Main
                lock.lock(); // with a Lock in Main
                    c1.agafarCobert();
                    System.out.println(name + ": Agafa C1");
                    c2.agafarCobert();
                    System.out.println(name + ": Agafa C2");
                lock.unlock();
                //}
                System.out.println("Filosof " + name + " està menjant!");
                Thread.sleep((long) ((Math.random() * 300) + 200));

                c1.deixarCobert();
                c2.deixarCobert();
                System.out.println("Filosof " + name + " PENSANDOR!");
                Thread.sleep((long) ((Math.random() * 200) + 200));
            }
        } catch (
                InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
