package UF2.ThreadsAndThings.FactorialTask.Multiplicacio;

import java.util.ArrayList;
import java.util.List;

public class MultiplicaNoCallablesMain {
    public static final int MAX = 5;

    public static void main(String[] args) throws Exception {
        //ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        List<MultiplicacioNoThreads> llistaTasques= new ArrayList<>();

        for (int i = 0; i < MAX; i++) {
            MultiplicacioNoThreads calcula = new MultiplicacioNoThreads((int)(Math.random()*10), (int)(Math.random()*10));
            llistaTasques.add(calcula);

        }
        List<Integer> llistaResultats = new ArrayList<>();

        long temp1 = System.currentTimeMillis(); //agafem els milisegons de la data
        for (MultiplicacioNoThreads llistaTasque : llistaTasques) {
            llistaResultats.add(llistaTasque.call());
        }

        long temp2 = System.currentTimeMillis();  //tornem a capturar els milisegons per calcular quan ha trigat

        for (int i = 0; i < llistaResultats.size(); i++) {
            Integer resultat = llistaResultats.get(i);
            System.out.printf("El resultat de la tasca %d és %d%n", i, resultat);
        }

        System.out.printf("Ha trigat: %d milisegons", (temp2-temp1));


    }
}