package UF2ThreadsAndThings.Monitor.LosFumadores;

public class Persona extends Thread {
    PersonType type;
    SourceType source;
    Mesa mesa;

    public Persona(PersonType type, SourceType source, Mesa mesa) {
        this.type = type;
        this.source = source;
        this.mesa = mesa;
    }

    @Override
    public void run() {
        if (type.equals(PersonType.PROVEEDOR)) proveer();
        else fumar();
    }

    public void fumar() {
        mesa.takeSources();
    }

    public void proveer() {
        if (this.type.equals(PersonType.PROVEEDOR)) {

        }
    }

}

