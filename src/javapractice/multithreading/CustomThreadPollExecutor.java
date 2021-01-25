package javapractice.multithreading;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

class MyTaskPlan implements Runnable {

    private String name;

    public MyTaskPlan(String name){
       this.name = name;
    }

    @Override
    public void run(){
        System.out.println(name+" task is executed by  "+Thread.currentThread().getName());
    }
}

class WorkerThread implements Runnable{

    private BlockingDeque blockingDeque;

    public WorkerThread(BlockingDeque blockingDeque){
        this.blockingDeque = blockingDeque;
    }

    @Override
    public void run() {

        while (!CustomThreadPool.isTerminated){
            MyTaskPlan myTaskPlan = null;
            try {
                 myTaskPlan = (MyTaskPlan) blockingDeque.take();
                 myTaskPlan.run();
                System.out.println("current thread started : "+Thread.currentThread().getName());
            }catch (Exception ex){
                System.out.println("exception in : "+Thread.currentThread().getName());
            }
            System.out.println("current thread ended : "+Thread.currentThread().getName());
        }
        System.out.println("thread terminated : "+Thread.currentThread().getName());

    }

}

class CustomThreadPool {

    public static volatile boolean isTerminated = false;

    BlockingDeque<Runnable> blockingDeque;

    public CustomThreadPool(int poolSize,int queueSize){
        blockingDeque = new LinkedBlockingDeque<Runnable>(queueSize);
        for(int i=0;i<poolSize;i++){
            Thread workerThread = new Thread(new WorkerThread(blockingDeque));
            workerThread.setName("WorkerThread-"+i);
            workerThread.start();
        }
    }

    public void execute(Runnable myTaskPlan){
        try {
            blockingDeque.put(myTaskPlan);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isShutdown(){
        return isTerminated;
    }

    public boolean shutdown(){
        this.isTerminated = true;
        return true;
    }

}

public class CustomThreadPollExecutor {

    public static void main(String[] args) {
        int noOfThread=4;
        CustomThreadPool customThreadPool = new CustomThreadPool(2,2);
        for(int i=0;i<noOfThread;i++){
            Runnable runnable = new MyTaskPlan("MytaskThread-"+i);
            customThreadPool.execute(runnable);
        }

        customThreadPool.shutdown();
    }
}
