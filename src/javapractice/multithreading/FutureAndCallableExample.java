package javapractice.multithreading;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class FactorialCalculator implements Callable<Long>{

    private int num;

    public FactorialCalculator(int num){
        this.num = num;
    }

    @Override
    public Long call() throws Exception {
        return calculateFactorial(num);
    }

    private Long calculateFactorial(int num) {
        long result= 1;
        while (num != 0){
            result *= num;
            num--;
        }
        return result;
    }
}

public class FutureAndCallableExample {

    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        int num =5;
        System.out.println("factorial calculation for num : "+num);
        Future<Long> result = executorService.submit(new FactorialCalculator(num));
        if(result.isDone()){
            System.out.println("factorial "+result.get());
        }
        executorService.shutdown();


    }
}
