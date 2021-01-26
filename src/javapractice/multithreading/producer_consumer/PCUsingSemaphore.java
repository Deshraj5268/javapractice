package javapractice.multithreading.producer_consumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class PCUsingSemaphore {

    public static void main(String[] args) {
        Semaphore emptySlots = new Semaphore(10);
        Semaphore fullSlots = new Semaphore(0);
        Semaphore mutex = new Semaphore(1);
        Shared shared = new Shared(new LinkedList<>());
        SemaphoreProducer semaphoreProducer = new SemaphoreProducer(emptySlots,fullSlots,mutex,shared);
        SemaphoreConsumer semaphoreConsumer = new SemaphoreConsumer(emptySlots,fullSlots,mutex,shared);
        Thread producerThread = new Thread(semaphoreProducer);
        Thread counsumerThread = new Thread(semaphoreConsumer);
        producerThread.start();
        counsumerThread.start();

    }

}

class SemaphoreProducer implements Runnable {

    private Semaphore emptySlots;
    private Semaphore fullSlots;
    private Semaphore mutex;
    private Shared shared;

    public SemaphoreProducer(Semaphore emptySlots, Semaphore fullSlots, Semaphore mutex, Shared shared) {
        this.emptySlots = emptySlots;
        this.fullSlots = fullSlots;
        this.mutex = mutex;
        this.shared = shared;
    }

    @Override
    public void run() {
        producer();

    }

    /*
    * down the empty slot
    * acquire the mutual exclusion
    * up the empty slots
    * */
    private void producer(){
        int val=0;
         while (true){
             try {
                 emptySlots.acquire(); // down the empty slot
                 mutex.acquire();
                 //critical section start
                 shared.list.offer(val);
                 System.out.println(val +" produced ");
                 val++;
                 Thread.sleep(1000);
                 //critical section end
                 mutex.release();
                 fullSlots.release();// up th full slots

             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
    }
}

class SemaphoreConsumer implements Runnable{

    private Semaphore emptySlots;
    private Semaphore fullSlots;
    private Semaphore mutex;
    private Shared shared;

    public SemaphoreConsumer(Semaphore emptySlots, Semaphore fullSlots, Semaphore mutex, Shared shared) {
        this.emptySlots = emptySlots;
        this.fullSlots = fullSlots;
        this.mutex = mutex;
        this.shared = shared;
    }

    @Override
    public void run() {
        consumer();
    }

    /*
    * down the full list
    * acquire mutual exclusion
    * up the empty slots
    * */
    private void consumer(){

        while (true){

            try {
                fullSlots.acquire(); // down the full list
                mutex.acquire();
                //critical section start
                System.out.println("consumed "+shared.list.poll());
                Thread.sleep(1000);
                //critical section end

                mutex.release();
                emptySlots.release();


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Shared{
    public Queue<Integer> list;

    public Shared(Queue<Integer> list) {
        this.list = list;
    }
}
