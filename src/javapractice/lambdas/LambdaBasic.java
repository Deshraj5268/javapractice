package javapractice.lambdas;


interface Greeting{

    void perform();
}

class HelloWorld implements Greeting {

    @Override
    public void perform(){
        System.out.println("hello  world !");
    }
}

public class LambdaBasic {

    public static void main(String[] args) {
        Greeting greeting = new HelloWorld();

        Greeting lambdaGreeting = ()->System.out.println("hello  World !");

       greeting.perform(); // calling function of class

       lambdaGreeting.perform(); // calling function or lambda only

        //anonymous class
        Greeting anonymousClass = new Greeting() {
            @Override
            public void perform() {
                System.out.println("hello  World !");
            }
        };
        anonymousClass.perform();


    }
}
