package javapractice.multithreading;

class Printer{

    /*public void printDocument(String documentName,int noOfCopy){
        synchronized(this) {
            for (int i = 0; i < noOfCopy; i++) {
                System.out.println("copy number :" + i + " is printed for document " + documentName);
            }
        }
    }*/

    public void printDocument(String documentName,int noOfCopy){
        for (int i = 0; i < noOfCopy; i++) {
            try {
                System.out.println("copy number :" + i + " is printed for document " + documentName);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class MyTask implements Runnable{

    private Printer printer;

    public MyTask(Printer printer){
        this.printer = printer;
    }
    @Override
    public void run(){
        synchronized(printer) {
            printer.printDocument("MyTask", 5);
        }
    }
}

class MyTask2 implements Runnable{
    private Printer printer2;

    public MyTask2(Printer printer2){
        this.printer2 = printer2;
    }

    @Override
    public void run(){
        synchronized(printer2) {
            printer2.printDocument("MyTask2", 5);
        }
    }
}

public class ThreadCreation {

    public static void main(String[] args) {

        Printer printer = new Printer();

        Thread myThread1 = new Thread(new MyTask(printer));
        myThread1.start(); /// myThread1.run(); --same stack
        //myThread1.setDaemon(true);

        Thread myThread2 = new Thread(new MyTask2(printer));
        myThread2.start(); /// myThread1.run(); --same stack

        System.out.println("main thread");
    }
}
