package UF2.ThreadsAndThings.FactorialTask.DivisioEnter;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class DivisioEnterTask extends RecursiveTask<Integer> {

    public int x;
    public final int y;
    private static int LLINDAR = 10;

    public DivisioEnterTask(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private int divisioS() {
        int cont = 0;
        while(x >= y){
            cont++;
            x -= y;
        }
        return cont;
    }

    public int divisioR(){
        DivisioEnterTask det = new DivisioEnterTask(x-y,y);
        det.fork();
        return det.join()+1;
    }

    @Override
    protected Integer compute() {
        if(x > LLINDAR) {
            return divisioR();
        }else return divisioS();
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        DivisioEnterTask def = new DivisioEnterTask(100,5);
        pool.invoke(def);
        int result2 = def.join();
        System.out.println(result2);
    }
}
