package javapractice.multithreading.producer_consumer;

import java.util.LinkedList;
import java.util.Queue;

class Producer implements Runnable {

    private Queue<Integer> queue;
    private int size;

    public Producer(Queue<Integer> queue, int size) {
        this.queue = queue;
        this.size = size;
    }

    private void produce(int i) {
        synchronized (queue) {
            while (queue.size() == size) {
                System.out.println("queue is full " + queue.size());
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("thread InterruptedException ....");
                }
            }
        }
        synchronized (queue) {
            System.out.println(i + " item produced by : " + Thread.currentThread().getName());
            queue.add(i);
            queue.notifyAll();
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 7; i++) {
            produce(i);
        }
    }
}

class Consumer implements Runnable{

    private Queue<Integer> queue;
    private int size;

    public Consumer(Queue<Integer> queue,int size){
        this.queue = queue;
        this.size = size;
    }

    private int consume() throws InterruptedException{
        synchronized (queue){
            while (queue.isEmpty()){
                System.out.println("item can not be consumed  because queue size : "+queue.size());
                queue.wait();
            }
        }
        synchronized (queue){
            System.out.print("item consumed by : "+Thread.currentThread().getName());
            queue.notifyAll();
            return queue.poll();
        }
    }

    @Override
    public void run(){
        for (int i = 0; i < 7; i++) {
            try {
                int consumed = consume();
               /* if(consumed == 0){
                    break;
                }*/
                System.out.println("consumed : " + consumed);
                Thread.sleep(500);
            }catch (InterruptedException e){
                System.out.println(e);
            }
        }
    }
}

public class PCUsingWaitNotify {

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        int size = 5;
        Thread producer = new Thread(new Producer(queue,size),"ProducerThread");
        Thread consumer = new Thread(new Consumer(queue,size),"ConsumerThread");
        producer.start();
        consumer.start();
    }
}
