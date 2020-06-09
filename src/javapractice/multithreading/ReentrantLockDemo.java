package javapractice.multithreading;

class A{

      void  test(){
        System.out.println("i m in A class");
    }
}


class B extends A {

    void test(){
        System.out.println("i m in B class");
    }
}
public class ReentrantLockDemo {

    public static void main(String[] args) {

        A b = new A();

        B b1 = (B)b;
        b1.test();
    }
}
