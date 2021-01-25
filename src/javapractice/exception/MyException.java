package javapractice.exception;

public class MyException {


    public static int  stackOverflowErr(int i){
        return stackOverflowErr(i+1);
    }

    public static void main(String[] args) {

        try{
            stackOverflowErr(0);
        }catch (StackOverflowError e){
            System.out.println("stack ldld");
        }
    }
}
