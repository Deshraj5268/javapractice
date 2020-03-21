package javapractice.multithreading;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class Task implements Runnable{

    private String taskName;

    public Task(String taskName){
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    @Override
    public void run() {
        try {
            long duration = (long) Math.random() * 10;
            System.out.println("executing :" + taskName);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

public class SimpleThreadPoolExecutor {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        for (int i=0;i<5;i++){
            Task task = new Task("task "+i);
            System.out.println("created "+task.getTaskName());
            threadPoolExecutor.execute(task);
        }
        threadPoolExecutor.shutdown();
    }
}
