package UF2ThreadsAndThings.Monitor.LosFumadores;

import java.util.ArrayList;

public class Mesa {
    ArrayList<SourceType> sourceList;

    public Mesa() {
        sourceList = new ArrayList<>();
    }

    public synchronized void putSource(SourceType source1, SourceType source2) {
        if (sourceList.size() != 0) {
            sourceList.add(source1);
            sourceList.add(source2);
            notifyAll();
        }else{
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public synchronized void takeSources(SourceType source) {
        if (sourceList.size() != 0 && !sourceList.contains(source)) {
            sourceList.clear();
            notifyAll();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
