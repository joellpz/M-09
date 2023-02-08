package UF2.ThreadsAndThings.Monitor.BanyMixt;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        BanyMixt banyMixt = new BanyMixt(5);
        ArrayList<Persona> personas = new ArrayList<>();
        personas.add(new Persona("HA","hombre", banyMixt));
        personas.add(new Persona("HB","hombre", banyMixt));
        personas.add(new Persona("MA","mujer", banyMixt));
        personas.add(new Persona("MB","mujer", banyMixt));
        personas.add(new Persona("NIÑOA","niño", banyMixt));
        personas.add(new Persona("NIÑOB","niño", banyMixt));
        /*for (int i = 0; i < 15; i++) {
            int num= (int) ((Math.random() * 3) + 1);
            switch (num) {
                case 1:
                    personas.add(new Persona("hombre", banyMixt));
                    break;
                case 2:
                    personas.add(new Persona("mujer", banyMixt));
                    break;
                case 3:
                    personas.add(new Persona("niño", banyMixt));
                    break;
            }
        }*/
        personas.forEach(persona -> {
            System.out.println(persona.getPersonName());
            persona.start();
        });
    }
}



//Exercicis de Monitors
//
//Bany Mixt A l'institut hi ha un bany mixte que cal gestionar el seu ús per (dones, homes i nens)
//Té una capacitat limitada
//No poden haver-hi a la vegada, a dins, persones de diferent tipus
//Tasca infinita de les persones: treballar i deprés anar al labavo