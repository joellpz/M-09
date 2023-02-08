package UF2.ThreadsAndThings.Monitor.PotDeGaletes;

public class MainGaletes {
    public static void main(String[] args) {
        PotGaletes pot = new PotGaletes();
        Pare pare = new Pare(pot, "pare");
        Fill fill1 = new Fill(pot, "fill-1");
        Fill fill2 = new Fill(pot, "fill-2");

        pare.start();
        fill2.start();
        fill1.start();
    }
}
