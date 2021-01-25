package javapractice.multithreading;

class MyThread extends Thread{

    public MyThread(String threadName){
        super(threadName);
    }

    @Override
    public void run(){
        System.out.println("i m in : "+Thread.currentThread().getName());
    }
}

public class MyThreadUsingThread {

    public static void main(String[] args) {
        MyThread myThread = new MyThread("myThread");
        Thread thread = new Thread(new MyThread("t2"));// thread of thread
        myThread.start();
        thread.start();

      /* output :  i m in : myThread
        i m in : Thread-0   t2 is new thread or task for thread*/
    }
}
