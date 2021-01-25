package javapractice.multithreading;

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

public class EvenOddUsingThread {

    public static void main(String[] args) {
        int count = 5;
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
        oddThread.start();


    }
}
