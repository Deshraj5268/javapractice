package javapractice.collection;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class TreeAndTreeSetpractice {

    public static void main(String[] args) {
        TreeMap<String,Set<String>> dictionary =
                new TreeMap<String,Set<String>>();
        Set<String> aWords = new TreeSet<>
                (Arrays.asList("Alive","Awesome","All"));
        Set<String> bWords = new TreeSet<>
                (Arrays.asList("Be","Banana","Ball"));
        dictionary.put("B", bWords);
        dictionary.put("A", aWords);
        System.out.println(dictionary);
    }
}
