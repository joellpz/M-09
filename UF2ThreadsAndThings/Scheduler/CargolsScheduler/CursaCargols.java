package UF2ThreadsAndThings.Scheduler.CargolsScheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by jordi on 06/12/16.
 * Mòdul M9. Activitat 2. Exercici 1
 * Necessita de Cargolsjava i monitorCargols.java
 * Main del exercici 1. Arrencada dels Cargols amb SchedulerExecutorService
 */

public class CursaCargols {

    public static void main(String[] args) {
        Cargol treubanya = new Cargol("Treubanya");
        MonitorCargols mTreubanya = new MonitorCargols(treubanya);
        Cargol bover = new Cargol("Bover");
        MonitorCargols mBover = new MonitorCargols(bover);

        ScheduledExecutorService schExService = Executors.newScheduledThreadPool(3);
        schExService.scheduleWithFixedDelay(treubanya, 2, 1, TimeUnit.SECONDS);
        schExService.scheduleWithFixedDelay(bover, 4, 4, TimeUnit.SECONDS);
        schExService.scheduleWithFixedDelay(mTreubanya, 5, 1, TimeUnit.SECONDS);
        schExService.scheduleWithFixedDelay(mBover, 3, 5, TimeUnit.SECONDS);

        // Esperem a que passin els 25s o bé a que acabin tots
        try {
            schExService.awaitTermination(25, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        schExService.shutdownNow();

        System.out.println("\nTotal "+ treubanya.getNom() + ":" + treubanya.getMetres());
        System.out.println("Total "+ bover.getNom() + ":" + bover.getMetres());
    }
}