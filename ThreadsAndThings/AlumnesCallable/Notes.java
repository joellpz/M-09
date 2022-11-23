package ThreadsAndThings.AlumnesCallable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class Notes {

    public static void main(String[] args) throws InterruptedException {
        int numAlumnes = 10,te,ti;
        String Modul = "M9";
        List<Alumne> alumnesExamen = new ArrayList<>();
        //int notes[] = new int[numAlumnes];
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);



        //instanciem els alumnes
        //Alumnes.Alumne[] A_m9 = new Alumnes.Alumne[numAlumnes];

        //comencem a contar el temps
        ti = (int) System.currentTimeMillis();
        //Donem nom als alumnes i els posem a fer l'examen
        for (int i=0;i<numAlumnes;i++) {
            Alumne nota = new Alumne(Modul+"-"+i);
            alumnesExamen.add(nota);
            /*A_m9[i] = new Alumnes.Alumne(Modul+"-"+i);
            notes[i] = A_m9[i].Examinar();*/
        }

        List<Future<Integer>> notesAlumnes;
        notesAlumnes = executor.invokeAll(alumnesExamen);
        executor.shutdown();
        //Han acabat i agafem el temps final
        te = (int) System.currentTimeMillis();

        //traiem els resultats i el temps que han trigat
        for (int i=0;i<numAlumnes;i++) {
            Future<Integer> result = notesAlumnes.get(i);
            try {
                System.out.println("Alumnes.Alumne "+alumnesExamen.get(i).getNom()+" : " + result.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Han trigat: " + (te - ti)/1000 + " segons");
    }



}