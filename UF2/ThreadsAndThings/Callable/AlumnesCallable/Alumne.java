package UF2.ThreadsAndThings.Callable.AlumnesCallable;

import java.util.concurrent.Callable;

public class Alumne implements Callable<Integer> {
    private String Nom;

    public Alumne(String nom) {
        Nom = nom;
    }

    public int Examinar() {

        //temps que triga a fer l'examen
        try {
            Thread.sleep((long) (Math.random()*2000)+1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //nota de l'examen
        return (int) (Math.random()*10);
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }


    @Override
    public Integer call() {
        return Examinar();
    }
}