package UF2.ThreadsAndThings.Scheduler.JocScheduler;

public class MonitorJugador implements Runnable {
    private final Jugador jugador;

    public MonitorJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    @Override
    public void run() {
        System.out.println(" -- Actualment el " + jugador.getNom() + " te "
                + jugador.getPunts() + " punts -- ");
    }
}
