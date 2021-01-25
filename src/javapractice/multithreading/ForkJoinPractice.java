package javapractice.multithreading;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPractice extends RecursiveTask<Integer>{

    private int num;

    public ForkJoinPractice(int n){
        this.num = n;
    }

    @Override
    protected Integer compute() {

        if(num<=1){
            return num;
        }
        ForkJoinPractice forkJoinPractice1 = new ForkJoinPractice(num-1);
        forkJoinPractice1.fork();

        ForkJoinPractice forkJoinPractice2 = new ForkJoinPractice(num-2);
        forkJoinPractice2.fork();

        return forkJoinPractice1.join() + forkJoinPractice2.join();
    }

    public static void main(String[] args) {

        int n = 30;
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

        System.out.println("fibonacci number "+forkJoinPool.invoke(new ForkJoinPractice(n)));



    }
}
