package ThreadsAndThings.PaletaRunnable;

public class Paleta implements Runnable {
     String name;
     int numMaons;
    public Paleta(String name, int numMaons){
        this.name = name;
        this.numMaons = numMaons;
    }

    public void setNumMaons(int numMaons) {
        this.numMaons = numMaons;
    }

    public String getName() {
        return name;
    }

    public void posarMao(){
        System.out.println(name+": Està possant maons...");
        try {
            Thread.sleep((long) ((Math.random()*2000)+1000)*numMaons);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + ": Maons possat!");
    }

    @Override
    public void run() {
        posarMao();
    }
}
