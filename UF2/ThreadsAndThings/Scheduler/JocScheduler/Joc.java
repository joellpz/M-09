package UF2.ThreadsAndThings.Scheduler.JocScheduler;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Joc {
    public static void main(String[] args) {
        int numJugadors = 2;
        Map<Jugador, MonitorJugador> jugadorsList = new HashMap<>();
        ScheduledExecutorService schExService = Executors.newScheduledThreadPool(3);
        for (int i = 1; i <= numJugadors; i++) {
            Jugador j = new Jugador("Jugador" + i);
            jugadorsList.put(j, new MonitorJugador(j));
        }

        jugadorsList.forEach((j, m) -> {
            schExService.scheduleWithFixedDelay(j, (long) (Math.random() * 5) + 1, (long) (Math.random() * 5) + 1, TimeUnit.SECONDS);
            schExService.scheduleWithFixedDelay(m, (long) (Math.random() * 5) + 1, (long) (Math.random() * 5) + 1, TimeUnit.SECONDS);
        });

        try {
            schExService.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        schExService.shutdownNow();

        jugadorsList.keySet().stream()
                .sorted((o1, o2) -> Integer.compare(o2.getPunts(), o1.getPunts()))
                .forEach(System.out::println);


    }
}
