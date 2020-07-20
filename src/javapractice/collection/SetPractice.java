package javapractice.collection;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class SetPractice {

    public static void main(String[] args) {
        findUniqueElementFromList();
    }

    private static void findUniqueElementFromList() {
        List<Integer> ls = new LinkedList<>();
        ls.add(2);
        ls.add(5);
        ls.add(5);
        Set<Integer> set = new HashSet<>(ls);
        for(Integer it:set){
            System.out.println(it);
        }
    }
}
