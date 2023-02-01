package UF2ThreadsAndThings.Examen.Ascensor;

public class Persona extends Thread {
    private final String name;
    private Ascensor ascensor;
    private int floorToGo;
    private final int floorFrom;

    public Persona(String name, Ascensor ascensor) {
        this.name = name;
        this.ascensor = ascensor;
        this.floorFrom = (int) ((Math.random() * 10) + 1);
        do {
            this.floorToGo = (int) ((Math.random() * 10) + 1);
        } while (floorToGo == floorFrom);
        System.out.println(name + " quiere ir a "+ floorToGo + " y esta en "+floorFrom);
    }

    @Override
    public void run() {
        ascensor.takeElevator(floorFrom);
        System.out.println(name + " volia Pujar al "+floorFrom);
        ascensor.leaveElevator(floorToGo);
        System.out.println(name+" volia BAIXAR al-> "+floorToGo);
    }
}
