package UF2.ThreadsAndThings.Monitor.CursaRelleus;

public class Equip extends Thread {
    long temps_inicial; //agafem els milisegons de la data
    float temps_final = System.currentTimeMillis(); //agafem els milisegons de la data

    String name;
    Atleta a1, a2, a3, a4;

    public Equip(String name, Atleta a1, Atleta a2, Atleta a3, Atleta a4) {
        this.name = name;
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        this.a4 = a4;
    }

    @Override
    public void run() {
        System.out.println(name + " empieza a correr");
        this.temps_inicial = System.currentTimeMillis();
        a1.start();
        a2.start();
        a3.start();
        a4.start();

        try {
            a1.join();
            a2.join();
            a3.join();
            a4.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        temps_final = (float) (System.currentTimeMillis() - temps_inicial) / 1000;
        //System.out.println(name + " ha trigat: " +temps_final);
    }


    public float getTemps_final() {
        return temps_final;
    }

}
