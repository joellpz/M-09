package UF2ThreadsAndThings.Runnable.Supermercat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Supermercat {
    public static List<Float> carrito = new ArrayList<>();
    static Random r = new Random();
    static float rangeMin = 0.0f;
    static float rangeMax = 20.0f;
    public static void main(String[] args) {

        int numClients = 20;
        int numCaixes = 2;
        ArrayList<Client> llistaClients1 = new ArrayList<>();
        ArrayList<Client> llistaClients2 = new ArrayList<>();
        List<Caixa> llistaCaixes = new ArrayList<>();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

        for (int i = 0; i < numClients; i++) {
            llistaClients1.add(new Client("client"+i, generarCarrito()));
            llistaClients2.add(new Client("client"+i, generarCarrito()));
        }
        for (int i = 0; i < numCaixes; i++) {
            llistaCaixes.add(new Caixa("caixa"+i));
        }
        llistaCaixes.get(0).setClients(llistaClients1);
        llistaCaixes.get(1).setClients(llistaClients2);

        for (int i = 0; i < numCaixes; i++) {
            executor.execute(llistaCaixes.get(i));
        }
        executor.shutdown();
    }

    public static List<Float> generarCarrito(){
        carrito.clear();
        for (int i = 0; i < Math.random()*20; i++) {
            carrito.add((float) (rangeMin + (rangeMax - rangeMin) * r.nextDouble()));
        }
        return carrito;
    }
}
