package javapractice.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UnModifiableCollection {

    public static void main(String[] args) {

        List<Integer> readOnlyList = new ArrayList<>();

        readOnlyList.add(2);
        readOnlyList.add(3);

        System.out.println("before unmodifiable .. ");

        readOnlyList.stream().forEach(data-> System.out.println(data));

        readOnlyList =  Collections.unmodifiableList(readOnlyList);

        System.out.println("after unmodifiable .. ");

       // readOnlyList.add(4);  java.lang.UnsupportedOperationException

        readOnlyList.stream().forEach(data-> System.out.println(data));
    }
}
