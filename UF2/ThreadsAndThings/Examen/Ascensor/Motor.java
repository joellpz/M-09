package UF2.ThreadsAndThings.Examen.Ascensor;

public class Motor extends Thread {
    private final Ascensor ascensor;

    public Motor(Ascensor ascensor) {
        this.ascensor = ascensor;
    }

    @Override
    public void run() {
        try {
            do {
                if (ascensor.getState().equals("up")) {
                    ascensor.floorUp();
                    Thread.sleep(3000);
                } else{
                    ascensor.floorDown();
                    Thread.sleep(3000);
                }
            } while (true);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
