package UF2.ThreadsAndThings.Runnable.PaletaRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FerParet {
    public static final int numMaons = 5;
    public static final int numPaletas = 10;

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        List<Paleta> llistaPaletas = new ArrayList<>();
        long temp1 = System.currentTimeMillis(); //agafem els milisegons de la data

        for (int i = 0; i < numPaletas; i++) {
            Paleta p = new Paleta("paleta"+i, numMaons);
            llistaPaletas.add(p);
            executor.execute(p);
        }
        executor.shutdown();
        executor.awaitTermination(10000,TimeUnit.SECONDS);
        long temp2 = System.currentTimeMillis(); //agafem els milisegons de la data
        System.out.println("Han trigat: " + (temp2 - temp1)/1000 + " segons");
    }
}
