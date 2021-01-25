package javapractice.collection;

import java.util.Map;
import java.util.TreeMap;

class Student implements Comparable<Student>{ // need to implement

    int id;
    String name;


    @Override
    public int compareTo(Student o) {
        return this.id> o.id ? 1:-1;
    }
}

public class TreeMapPractice {
    public static void main(String[] args) {

        TreeMap treeMap = prepareMap();
        System.out.println("descending ");
        prepareDescendingMap();

        System.out.println("descendingMap: "+treeMap.descendingMap());
        System.out.println("headMap: "+treeMap.headMap(101,true)); // 0 to =n

        TreeMap<Student,String> objetTree = new TreeMap<>();
        objetTree.put(new Student(),"ram");
        objetTree.put(new Student(),"syam");
        System.out.println("objetTree: "+objetTree.descendingMap());

    }

    private static TreeMap prepareMap() {

        TreeMap<Integer,String> treeMap=new TreeMap<Integer,String>();
        treeMap.put(100,"Amit");
        treeMap.put(102,"Ravi");
        treeMap.put(101,"Vijay");
        treeMap.put(103,"Rahul");

        for(Map.Entry m:treeMap.entrySet()){
            System.out.println(m.getKey()+" "+m.getValue());
        }
        return treeMap;
    }

    private static TreeMap prepareDescendingMap() {

        TreeMap<Integer,String> treeMap=new TreeMap<Integer,String>((k1,k2)->{ return (Integer.valueOf(k1)>Integer.valueOf(k2))? -1: 1;});
        treeMap.put(100,"Amit");
        treeMap.put(102,"Ravi");
        treeMap.put(101,"Vijay");
        treeMap.put(103,"Rahul");

        for(Map.Entry m:treeMap.entrySet()){
            System.out.println(m.getKey()+" "+m.getValue());
        }
        return treeMap;
    }
}
