package javapractice.multithreading;

import java.util.concurrent.Semaphore;

class Shared {
    public static int count = 0;
}

class SemaphoreThread extends Thread{

    private Semaphore semaphore;
    private String name;

    public SemaphoreThread(Semaphore semaphore, String name){
        super(name);
        this.semaphore = semaphore;
        this.name = name;
    }

    @Override
    public void run() {

        if ("A".equals(this.getName())) {
            System.out.println("starting : " + name);
            try {
                System.out.println(name + " thread is waiting for permit ...");
                semaphore.acquire();
                System.out.println(name + " thread is aquired the  permit ...");
                for (int i = 0; i < 5; i++) {
                    Shared.count++;
                    System.out.println(name + ": " + Shared.count);
                    //allow context switch if B can execute but due to semphore it cant not be happen
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(name + " thread release the  permit ...");
            semaphore.release();

        } else {
            //for thread B
            System.out.println("starting : " + name);
            try {
                System.out.println(name + " thread is waiting for permit ...");
                semaphore.acquire();
                System.out.println(name + " thread is aquired the  permit ...");
                for (int i = 0; i < 5; i++) {
                    Shared.count--;
                    System.out.println(name + ": " + Shared.count);
                    //allow context switch if B can execute but due to semphore it cant not be happen
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(name + " thread release the  permit ...");
            semaphore.release();

        }
    }
    }

public class SemaphoreDemo {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);
        SemaphoreThread s1 = new SemaphoreThread(semaphore,"A");
        SemaphoreThread s2 = new SemaphoreThread(semaphore,"B");

        s1.start();
        s2.start();

        //waiting for thread
        s1.join();
        s2.join();

        System.out.println("count: " + Shared.count);
    }
}
