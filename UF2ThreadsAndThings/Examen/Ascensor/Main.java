package UF2ThreadsAndThings.Examen.Ascensor;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Persona> personaList = new ArrayList<>();
        Ascensor ascensor = new Ascensor(10, 10);
        Motor motor = new Motor(ascensor);

        for (int i = 0; i < 5; i++) {
            personaList.add(new Persona(i +": ", ascensor));
        }

        personaList.forEach(Thread::start);
        motor.start();
    }
}
