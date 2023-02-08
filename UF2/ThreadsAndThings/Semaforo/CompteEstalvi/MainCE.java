package UF2.ThreadsAndThings.Semaforo.CompteEstalvi;

import java.util.ArrayList;

public class MainCE {
    public static void main(String[] args) {
        CompteEstalvi ce = new CompteEstalvi(0);

        Runnable ingresar = () -> ce.ingresar(100);
        Runnable retirar = () -> ce.retirar(50);
        int reps = 1000;
        ArrayList<Thread> threadsIng = new ArrayList<>();

        for (int i= 0; i<reps; i++){
            Thread thIng = new Thread(ingresar);
            Thread thRet = new Thread(retirar);


            thIng.start();
            thRet.start();
        }


    }
}
