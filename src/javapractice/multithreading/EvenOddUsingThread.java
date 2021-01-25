package javapractice.multithreading;

import java.util.concurrent.Semaphore;

class EvenOdd{

    private int count;
    private int max ;
    private boolean odd;

    public EvenOdd(int count, int max, boolean odd) {
        this.count = count;
        this.max = max;
        this.odd = odd;
    }

    public void printEven(){
        try{
            Thread.sleep(100);
        }catch (InterruptedException ex){
            System.out.println(ex.getMessage());
        }
        while (count < max) {
            synchronized (this) {
                while (odd) {
                    try {
                       // System.out.println("waiting : " + Thread.currentThread().getName());
                        this.wait();
                    } catch (InterruptedException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                System.out.println(Thread.currentThread().getName() + " :" + count);
                count++;
                odd = true;
                this.notify();
            }
        }
    }

    public void printOdd(){
        while (count<max) {
            synchronized (this) {
                while (!odd) {
                    try {
                       // System.out.println("waiting : " + Thread.currentThread().getName());
                        this.wait();
                    } catch (InterruptedException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                System.out.println(Thread.currentThread().getName() + " :" + count);
                count++;
                odd = false;
                this.notify();
            }
        }

    }
}

class ThreadMyOvenOdd implements Runnable{

    private int count;
    private volatile int max;
    private volatile boolean even;

    public ThreadMyOvenOdd(int count, int max, boolean even) {
        this.count = count;
        this.max = max;
        this.even = even;
    }

    @Override
    public void run() {

        while (count <= max)
        if("even".equals(Thread.currentThread().getName()) && even){

            synchronized (this){
                System.out.println("even : " +this.count);
                count++;
                even = false;
            }
        }else if("odd".equals(Thread.currentThread().getName()) && !even){

            synchronized (this){
                System.out.println("odd : "+ this.count);
                count++;
                even = true;
            }
        }

    }
}

class EvenOddThreadSemaphore{
    int flag;
    int count;
    int max;
    Semaphore semaphore;
    Semaphore mutex;

    public EvenOddThreadSemaphore(int flag, int count, int max, Semaphore semaphore,Semaphore mutex) {
        this.flag = flag;
        this.count = count;
        this.max = max;
        this.semaphore = semaphore;
        this.mutex = mutex;
    }

    public void evenPrintSemaphore(){
        while (count<=max) {
            try {
                mutex.acquire();
               // System.out.print(Thread.currentThread().getName() + " entered : ");
                if(count % 2 == 0) {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " :" + count);
                    count++;
                    semaphore.release();
                }
                mutex.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void oddPrintSemaphore(){
        while (count<=max) {
            try {
                mutex.acquire();
              //  System.out.print(Thread.currentThread().getName() + " entered : ");
                if(count % 2 != 0) {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " :" + count);
                    count++;
                    semaphore.release();
                }
                mutex.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class EvenOddUsingThread {

    public static void main(String[] args) {
        /*int count = 5;
        boolean odd = false;
        if(count % 2 == 1){
            odd = true;
        }
        EvenOdd evenOdd = new EvenOdd(count,10,odd);
        Thread evenThread = new Thread(new Runnable() {
            @Override
            public void run() {
                evenOdd.printEven();
            }
        },"evenThread"

        );

        Thread oddThread = new Thread(new Runnable() {
            @Override
            public void run() {
                evenOdd.printOdd();
            }
        },"oddThread"

        );

        evenThread.start();
        oddThread.start();*/
        /*int count=0;
        boolean evenflag= true;
        int max=9;
        ThreadMyOvenOdd  evenThread = new ThreadMyOvenOdd(count,max,evenflag);

        Thread even = new Thread(evenThread,"even");
        Thread odd = new Thread(evenThread,"odd");
        even.start();
        odd.start();*/

        EvenOddThreadSemaphore evenOddThreadSemaphore = new EvenOddThreadSemaphore(0,0,
                10,new Semaphore(1),new Semaphore(1));
        Thread evenT = new Thread(() -> evenOddThreadSemaphore.evenPrintSemaphore(),"even");
        Thread oddT = new Thread(() -> evenOddThreadSemaphore.oddPrintSemaphore(),"odd");
        evenT.start();
        oddT.start();


    }
}
