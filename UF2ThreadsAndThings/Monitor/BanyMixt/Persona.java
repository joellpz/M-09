package UF2ThreadsAndThings.Monitor.BanyMixt;

public class Persona extends Thread{
    private String name;
    private String type;
    private BanyMixt bany;

    public Persona(String name, String type, BanyMixt bany){
        this.type = type;
        this.bany = bany;
        this.name = name;
    }

    @Override
    public void run() {
        do {
            traballar();
            irBany();
        }while (true);
    }

    public String getPersonName() {
        return name;
    }

    private void irBany() {
        try {
            bany.entrarBany(type);
            System.out.println(name + " està en el BANY!");
            Thread.sleep((long) ((Math.random()*1000)+500));
            bany.sortirBany();
            Thread.sleep((long) (Math.random()*50));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void traballar() {
        try {
            System.out.println(name + " està TRABAJANDO!");
            Thread.sleep((long) ((Math.random()*1000)+500));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        };
    }
}
