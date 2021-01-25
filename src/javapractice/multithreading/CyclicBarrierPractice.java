package javapractice.multithreading;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.Level;
import java.util.logging.Logger;

class CabService implements Runnable{

    private static final Logger logger = Logger.getLogger(CabService.class.getName());
    private CyclicBarrier cyclicBarrier;

    public CabService(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run(){
        try {
            System.out.println(Thread.currentThread().getName()+" arrived");
            if("passenger2".equals(Thread.currentThread().getName())){
                new BrokenBarrierException();
            }
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            logger.log(Level.INFO,e.getMessage()+" Error");
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" is going to board cab");
    }
}

public class CyclicBarrierPractice {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        CabService cabService = new CabService(cyclicBarrier);
        //Same object sharing with three object,can use because no data is modified by the all thrad
        Thread t1 = new Thread(cabService, "passenger1");
        Thread t2 = new Thread(cabService, "passenger2");
        Thread t3 = new Thread(cabService, "passenger3");

        /*Thread t1 = new Thread(new CabService(cyclicBarrier), "passenger1");
        Thread t2 = new Thread(new CabService(cyclicBarrier), "passenger2");
        Thread t3 = new Thread(new CabService(cyclicBarrier), "passenger3");*/

        t1.start();
        t2.start();
        t3.start();
    }


}
