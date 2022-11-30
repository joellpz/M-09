package ThreadsAndThings.Monitor.PotDeGaletes;

public class Fill extends Thread {
    private PotGaletes potGaletes;
    private String nom;

    private int numGaleta;

    public Fill(PotGaletes potGaletes, String nom) {
        this.potGaletes = potGaletes;
        this.nom = nom;
    }

    public void menjarGaleta(int num) {
        System.out.println("Ñam Ñam");
        if (num > potGaletes.getNumGaletes()) {
            try {
                Thread.sleep(potGaletes.getNumGaletes()*100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            potGaletes.setNumGaletes(0);
        } else {
            try {
                Thread.sleep(num * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            potGaletes.setNumGaletes(potGaletes.getNumGaletes() - num);
        }
    }

    @Override
    public void run() {
        while (true) {
            potGaletes.agafat();
            System.out.println(nom + " ha agafat el pot!");
            numGaleta = (int) ((Math.random() * 2) + 2);
            if (potGaletes.getNumGaletes() == 0) {
                System.out.println("No hi han galetes :(");
                potGaletes.deixar();
                try {
                    Thread.sleep((long) Math.random() * 100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {

                System.out.println(numGaleta);
                menjarGaleta(numGaleta);
                potGaletes.deixar();
                try {
                    Thread.sleep((long) Math.random() * 100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
