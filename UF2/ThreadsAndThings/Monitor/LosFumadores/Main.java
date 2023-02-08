package UF2.ThreadsAndThings.Monitor.LosFumadores;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Mesa mesa = new Mesa();
        ArrayList<Persona> personas = new ArrayList<>();
        personas.add(new Persona(PersonType.FUMADOR,SourceType.FOSFOROS,mesa));
        personas.add(new Persona(PersonType.FUMADOR,SourceType.TABACO,mesa));
        personas.add(new Persona(PersonType.FUMADOR,SourceType.PAPEL,mesa));
        personas.add(new Persona(PersonType.PROVEEDOR, SourceType.FOSFOROS, mesa));

        personas.forEach(Thread::start);
    }
}
