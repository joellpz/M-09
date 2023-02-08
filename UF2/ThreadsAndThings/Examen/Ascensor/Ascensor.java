package UF2.ThreadsAndThings.Examen.Ascensor;

public class Ascensor {
    private final int maxFloor;
    private int actualFloor;
    private String state = "up";
    private final int maxPersonas;
    private int numPersonas;


    public Ascensor(int maxFloor, int maxCap) {
        this.maxFloor = maxFloor;
        this.maxPersonas = maxCap;
        this.actualFloor = 0;
        this.numPersonas = 0;
    }

    public synchronized void floorUp() {
        if (actualFloor < maxFloor) {
            actualFloor++;
            System.out.println("Ascensor SUBIENDO a " + actualFloor);
            notifyAll();
        } else state = "down";
    }

    public synchronized void floorDown() {
        if (actualFloor > 0) {
            actualFloor--;
            System.out.println("Ascensor BAJANDO a " + actualFloor);
            notifyAll();

        } else state = "up";
    }

    public synchronized void takeElevator(int floorFrom) {
        try {
            while (actualFloor != floorFrom || numPersonas == maxPersonas) {
                if (numPersonas == maxPersonas) {
                    System.out.println(" ** CAPACITAT PLENA NO PUJA**");
                }
                wait();
            }
            numPersonas++;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void leaveElevator(int floorToGo) {
        try {
            while (actualFloor != floorToGo) wait();
            numPersonas--;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized String getState() {
        return state;
    }
}
