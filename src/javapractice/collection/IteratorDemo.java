package javapractice.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class IteratorDemo {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(13);


        ListIterator<Integer> listIterator = list.listIterator();
        while (listIterator.hasNext()){
            System.out.println(listIterator.next());
        }
    }
}

