package UF2ThreadsAndThings.Monitor.LosFumadores;

import java.util.ArrayList;

public class Mesa {
    ArrayList<SourceType> sourceList;
    boolean thereAreSources;

    public Mesa() {
        sourceList = new ArrayList<>();
        thereAreSources = false;
    }

    public synchronized void putSource(SourceType source1, SourceType source2) {
        if (sourceList.size() == 0) {

            sourceList.add(source1);
            sourceList.add(source2);
            notifyAll();
        } else {
            try {
                System.out.println("PROVEEDOR: ESPERANDO!");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public synchronized void takeSources(SourceType source) {
        do {
            if (sourceList.size() != 0 && !sourceList.contains(source)) {
                System.out.print("\n"+source + ": ");
                sourceList.forEach(sourceType -> System.out.print(sourceType + ", "));
                System.out.println();
                sourceList.clear();
                notifyAll();
                break;
            } else {
                try {
                    //System.out.println(source + ": ESPERANDO!");
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        } while (true);
    }
}
