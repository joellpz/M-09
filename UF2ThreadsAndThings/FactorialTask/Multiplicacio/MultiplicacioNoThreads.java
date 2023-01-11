package UF2ThreadsAndThings.FactorialTask.Multiplicacio;

import java.util.concurrent.Callable;

public class MultiplicacioNoThreads implements Callable<Integer> {
    private final int op1;
    private final int op2;
    public MultiplicacioNoThreads(int op1, int op2) {
        this.op1=op1;
        this.op2=op2;
    }

    @Override
    public Integer call() throws Exception {
        Thread.sleep(2000);
        return op1*op2;
    }
}