package UF2.ThreadsAndThings.Monitor.PeleaTelevisor;

public class PeleaTelevisor {

    public static void main(String[] args) {
        Comandament com = new Comandament();

        MembreFamilia pare = new MembreFamilia(com, "pare");
        MembreFamilia mare = new MembreFamilia(com, "mare");
        MembreFamilia fill = new MembreFamilia(com, "fill");
        MembreFamilia iaia = new MembreFamilia(com, "iaia");
        MembreFamilia filla = new MembreFamilia(com, "filla");

        pare.start();
        mare.start();
        filla.start();
        iaia.start();
        fill.start();
    }
}
