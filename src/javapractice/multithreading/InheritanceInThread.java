package javapractice.multithreading;

class Base implements Runnable{

    @Override
    public void run(){
        System.out.println("i m in base class which  implements  Runnable interface ");
    }

    public void serviceViryfy(){

    }

}

class Child extends Base {

    @Override
    public void serviceViryfy() {
        //super.serviceViryfy();
        System.out.println("i m in child class");
    }
}

public class InheritanceInThread {

    public static void main(String[] args) {

        new Thread(new Child()).start();

    }
}
