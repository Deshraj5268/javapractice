package javapractice.lambdas;

import java.util.function.BiConsumer;

public class LambdaExercise2 {

    public static void main(String[] args) {

        int [] arr = {1,2,3,9,5};
        int key = 2;

       /* processWithKey(arr,key,(t,u)-> System.out.print((t + u)+" "));
        System.out.println("divide t/v");
        processWithKey(arr,key,(t,u)-> System.out.print((t / u)+" "));*/

        //Exception handling
        processWithKey(arr,key,wrapperProcessWithKey((t,u)-> System.out.print((t / u)+" ")));

    }

    private static void processWithKey(int [] arr, int key, BiConsumer<Integer,Integer> consumer){
        for (int i:arr){
          consumer.accept(i,key);// function call to wrapperProcessWithKey method
        }
    }

    private static BiConsumer<Integer,Integer> wrapperProcessWithKey(BiConsumer<Integer,Integer> consumer){
        return (t,u)-> {
            try {
                //System.out.println("i m in wrapper ");
                consumer.accept(t, u);
            } catch (Exception ex) {
                System.out.println("exception handling in lambda exprs");
            }
        };
    }
}
