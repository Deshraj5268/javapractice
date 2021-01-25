package javapractice.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

abstract class BaseHealthCheckUp implements Runnable{

    private CountDownLatch latch;
    private String serviceName;

    public BaseHealthCheckUp(String serviceName,CountDownLatch latch){
        this.serviceName = serviceName;
        this.latch = latch;
    }

    @Override
    public void run(){
        try {
            System.out.println("thread : "+Thread.currentThread().getName());
            verifyService();
            Thread.sleep(1000);
        }catch (InterruptedException ex){
            System.out.println(ex.getMessage());
           // isServiceUp = false;
        }finally {
            if(latch != null) {
                latch.countDown(); //while exception latch should be down otherwise deadlock can occur
            }
        }
    }

    public String getServiceName() {
        return serviceName;
    }

    public abstract boolean isServiceUp();

    public abstract void verifyService();
}

class NetworkHealthCheckUp extends BaseHealthCheckUp {

    private boolean isServiceUp;

    public NetworkHealthCheckUp(CountDownLatch latch){
        super("networkService",latch);
    }

    @Override
    public void verifyService(){

        System.out.println("checking status for : "+this.getServiceName());
        try{
            Thread.sleep(100);
        }catch (InterruptedException ex){
            System.out.println(ex.getMessage());
            return;
        }
        System.out.println(this.getServiceName()+" service is up now ...");
        isServiceUp = true;
    }

    @Override
    public boolean isServiceUp() {
        return isServiceUp;
    }
}

class DatabaseHealthCheckUp extends BaseHealthCheckUp {
    private boolean isServiceUp;

    public DatabaseHealthCheckUp(CountDownLatch latch){
        super("dataBaseService",latch);
    }

    @Override
    public void verifyService(){
        System.out.println("checking status for : "+this.getServiceName());
        try{
            Thread.sleep(1000);
        }catch (InterruptedException ex){
            System.out.println(ex.getMessage());
            return;
        }
        System.out.println(this.getServiceName()+" service is up now ...");
        isServiceUp = true;
    }

    @Override
    public boolean isServiceUp() {
        return isServiceUp;
    }

}

class ApplicationStartUpUtil{

    private ApplicationStartUpUtil(){

    }

    private static final ApplicationStartUpUtil INSTANCE = new ApplicationStartUpUtil();

    public static ApplicationStartUpUtil getSingleTonInstance(){
        return INSTANCE;
    }

    public static boolean externalServiceStatusCheck() throws Exception{
        CountDownLatch latch = new CountDownLatch(2);
        List<BaseHealthCheckUp> healthListServices = new ArrayList<>();
        healthListServices.add(new NetworkHealthCheckUp(latch));
        healthListServices.add(new DatabaseHealthCheckUp(latch));

        ExecutorService executor = Executors.newFixedThreadPool(healthListServices.size());
        for (final BaseHealthCheckUp service : healthListServices) {
            executor.execute(service);
        }
        latch.await();
        executor.shutdown();//need to shutdown
        for (final BaseHealthCheckUp service : healthListServices) {
            if (!service.isServiceUp()) {
                return false;
            }
        }
        return true;
    }
}

public class CountDownLatchDriver {

    public static void main(String[] args) {
        boolean result = false;
        try{
            result = ApplicationStartUpUtil.externalServiceStatusCheck();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        System.out.println("status : "+result);
    }


}
