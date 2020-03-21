package javapractice.multithreading;

class LockMethodAndBlock {



    public synchronized void syncMethod(){
        try {
            System.out.println("method "+Thread.currentThread().getName());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("i m in sync method --");
    }

    public void syncBlock(){
        synchronized(this){
            try {
                System.out.println("method "+Thread.currentThread().getName());
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("i m in sync block --"+Thread.currentThread().getName());
        }
    }
}

public class SynchMethodNBlockLocking {

    public static void main(String[] args) {
        LockMethodAndBlock obj = new LockMethodAndBlock();


        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.syncBlock();
            }
        },"syncBlock1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.syncMethod();
            }
        },"syncMethod").start();



        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.syncBlock();
            }
        },"syncBlock2").start();

    }
}
