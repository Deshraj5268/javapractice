package javapractice.multithreading;


class MyJoinThread extends Thread{

    public MyJoinThread(String name){
        super(name);
    }

    @Override
    public void run() {
        /*if(Thread.currentThread().getName().equals("t1")){
            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        }*/
        System.out.println("i m in : "+Thread.currentThread().getName()+" thread id: "+Thread.currentThread().getId());

    }
}


public class ThreadMethods {

    public static void main(String[] args) throws InterruptedException {

        MyJoinThread myJoinThread1 = new MyJoinThread("t1");
        MyJoinThread myJoinThread2 = new MyJoinThread("t2");
        myJoinThread1.setPriority(Thread.MIN_PRIORITY);
        myJoinThread2.setPriority(Thread.MAX_PRIORITY);


        myJoinThread1.start();
        myJoinThread2.start();
        myJoinThread1.join();



    }
}
