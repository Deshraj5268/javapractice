package javapractice.multithreading;


import java.util.concurrent.Semaphore;


class ReaderWriter {

    private Semaphore mutex;
    private Semaphore dbWrite;
    private int readerCount;
    private int writerCount;

    public ReaderWriter(Semaphore mutex, Semaphore dbWrite, int readerCount) {
        this.mutex = mutex;
        this.dbWrite = dbWrite;
        this.readerCount = readerCount;
    }

    public void reader(){
       // while (true){
            try {
                mutex.acquire();
                readerCount++;

                if(readerCount == 1){
                    dbWrite.acquire();
                }
                mutex.release();
                System.out.println("reading data ..."+"reader count "+readerCount +"writer count :"+writerCount
                        +Thread.currentThread().getName());
                mutex.acquire();
                readerCount--;

                if(readerCount == 0){
                    dbWrite.release();
                }
                mutex.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
      //  }
    }

    public void writer(){
       // while (true){
            try {
                dbWrite.acquire();
                System.out.println("writing in db .."+"reader count "+readerCount +" writer count :"+ (++writerCount)+
                        Thread.currentThread().getName());
                dbWrite.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
      //  }
    }
}

public class ReaderWriterProblem {
    public static void main(String[] args) {

        Semaphore mutex= new Semaphore(1);
        Semaphore dbWrite = new Semaphore(1);
        int rc = 0;

        Shared.count = 0;

        ReaderWriter readerWriter = new ReaderWriter(mutex,dbWrite,rc);

        Thread writerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                readerWriter.writer();
            }
        },"Writer-Thread"
        );

        Thread writerThread23 = new Thread(new Runnable() {
            @Override
            public void run() {
                readerWriter.writer();
            }
        },"Writer-Thread23"
        );

        Thread readerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                readerWriter.reader();
            }
        },"Reader-Thread"
        );

        Thread readerThread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                readerWriter.reader();
            }
        },"Reader-Thread2"
        );

        readerThread.start();
        readerThread2.start();
        writerThread.start();
        writerThread23.start();

    }
}
