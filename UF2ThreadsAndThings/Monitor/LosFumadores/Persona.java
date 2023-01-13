package UF2ThreadsAndThings.Monitor.LosFumadores;

import java.util.ArrayList;

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
        do {
            if (type.equals(PersonType.PROVEEDOR)) proveer();
            else fumar();
        } while (true);
    }

    public void fumar() {
        mesa.takeSources(source);
        System.out.println(source+" está FUMANDO");
        try {
            Thread.sleep((long) ((Math.random() + 300) + 3000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    ArrayList<Integer> nums = new ArrayList<>();
    ArrayList<SourceType> sourceTypes = new ArrayList<>();

    public void proveer() {
        if (this.type.equals(PersonType.PROVEEDOR)) {
            do {
                nums.clear();
                nums.add((int) (Math.random() * 3));
                nums.add((int) (Math.random() * 3));
            } while (nums.get(0) == nums.get(1));
            nums.forEach(num -> {
                switch (num) {
                    case 0 -> sourceTypes.add(SourceType.PAPEL);
                    case 1 -> sourceTypes.add(SourceType.FOSFOROS);
                    case 2 -> sourceTypes.add(SourceType.TABACO);
                    default -> System.out.println("**ERROR, DEFAULT**");
                }
                ;
            });
            mesa.putSource(sourceTypes.get(0), sourceTypes.get(1));
            System.out.println("*** PROVEEDOR: PONE " + sourceTypes.get(0) + " y " + sourceTypes.get(1) + "***");
            try {
                Thread.sleep((long) ((Math.random() + 1000) + 100));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            sourceTypes.clear();
        }
    }

}

