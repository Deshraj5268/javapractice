package javapractice.streams;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {

    public static void main(String[] args) {

        final List<String> listOfString = Stream.of("abc", "raju", "sayam", "koti")
                .filter(s -> s.contains("a"))
                .map(s -> s.toUpperCase())
                .collect(Collectors.toList());

        listOfString.forEach(System.out::println);
    }
}
