package ThreadsAndThings.Monitor.MesaRedonda;

public class Main {
    public static void main(String[] args) {
        Cobert cobert1 = new Cobert();
        Cobert cobert2 = new Cobert();
        Cobert cobert3 = new Cobert();
        Cobert cobert4 = new Cobert();
        Filosof f1 = new Filosof("Juan1",cobert4,cobert1);
        Filosof f2 = new Filosof("Kiko2",cobert1,cobert2);
        Filosof f3 = new Filosof("Antonio3",cobert2,cobert3);
        Filosof f4 = new Filosof("Pedro4",cobert3,cobert4);

        f1.start();
        f2.start();
        f3.start();
        f4.start();
    }
}
