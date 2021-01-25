package javapractice.lambdas;


import org.omg.CORBA.Object;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


@FunctionalInterface
interface MyFunctional {

    void myFirst();
    default void myDefaultMethod(String str){
        System.out.println(" sfbg");
    }
}
public class FunctionalInterfacePractice {

    public static void main(String[] args) {
        MyFunctional myFunctional = ()-> System.out.println("i m n functional interface");
        myFunctional.myFirst();

    }
}
