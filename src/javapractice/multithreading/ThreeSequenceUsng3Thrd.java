package javapractice.multithreading;


class SequencePrinting implements Runnable {

    public static int count;
    public  static int maxCount;
    private int remainder;
     static Object lock = new Object();

    public SequencePrinting(int remainder) {
        this.remainder = remainder;
    }

    @Override
    public void run(){
        while (count<maxCount){
            synchronized (lock) {
                while ((count % 3) != remainder) {
                    try {
                        lock.wait();
                    } catch (InterruptedException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                System.out.println("count is " + count + " using thread -" + Thread.currentThread().getName());
                count++;
                lock.notifyAll();
            }

        }
    }
}

public class ThreeSequenceUsng3Thrd {

    public static void main(String[] args) {

        SequencePrinting.count = 1;
        SequencePrinting.maxCount = 9;
        new Thread(new SequencePrinting(1),"seq1-Thread").start();

        new Thread(new SequencePrinting(2),"seq2-Thread").start();

        new Thread(new SequencePrinting(0),"seq3-Thread").start();
    }
}
