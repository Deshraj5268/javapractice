package javapractice.collection;

import java.util.*;

public class HashmapPractice {

    public static void main(String[] args) {

        Map<Integer,String> map = new HashMap<>();
        map.put(1,"thakur");
        map.put(3,"singh");
        map.put(2,"deshraj");

       //printHashMap(map);

        sortingHashMap1(map);

        sortBasedOnKey(map);

        sortBasedOnValue(map);


    }

    private static void printHashMap(Map<Integer, String> map) {
        for (Map.Entry<Integer,String> mapIt :map.entrySet()){
            System.out.println(mapIt.getKey()+" : "+mapIt.getValue());
        }

        System.out.println("printing key-value using keySet()");
        if(map != null){
            for(Integer key : map.keySet()){
                System.out.println(key+" : "+map.get(key));
            }
        }

        System.out.println("printing key-value using values()");
        if(map != null){
            for(String value : map.values()){
                System.out.println("value : "+value);
            }
        }
    }

    private static void sortBasedOnKey(Map<Integer, String> map) {

        ArrayList<Integer> arrayList = new ArrayList<>(map.keySet());
        Collections.sort(arrayList);
        Map<Integer,String> linkedHashMap = new LinkedHashMap<>();
        for (Integer key : arrayList){
            linkedHashMap.put(key,map.get(key));
        }
        System.out.println("sortedMap based on key using LinkedHashMap : ");
        for (Map.Entry<Integer, String> sortMap:linkedHashMap.entrySet()){
            System.out.println(sortMap.getKey()+" : "+sortMap.getValue());
        }
    }

    private static void sortBasedOnValue(Map<Integer, String> map) {

        System.out.println("va;ue ;;;;");
        map.entrySet()
                .stream()
                .sorted(Map.Entry.<Integer, String>comparingByValue())
                .forEach(System.out::println);

        //or

        List<Map.Entry<Integer,String>> list = new ArrayList<>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Integer, String>>() {
            @Override
            public int compare(Map.Entry<Integer, String> o1, Map.Entry<Integer, String> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        Map<String,Integer> linkedHashMap = new LinkedHashMap<>(); // key value type should be change
        for (Map.Entry<Integer, String> entry : list){
            linkedHashMap.put(entry.getValue(),entry.getKey());
        }

        System.out.println("sortedMap based on Value using LinkedHashMap : ");
        for (Map.Entry<String,Integer> sortMap:linkedHashMap.entrySet()){
            System.out.println(sortMap.getKey()+" : "+sortMap.getValue());
        }

        /*TreeMapPractice treeMap = new TreeMapPractice(new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        treeMap.putAll(map);

        Set<Map.Entry<Integer,String>> entrySet = treeMap.entrySet();
        System.out.println("sorted Map based on Value using TreeMapPractice : ");
        for (Map.Entry<Integer,String> sortMap:entrySet){
            System.out.println(sortMap.getKey()+" : "+sortMap.getValue());
        }*/


        //TreeMapPractice treeMap = new TreeMapPractice(Comparator<Map.Entry> o1)

        /*ArrayList<String> arrayList = new ArrayList<>(map.values());
        Collections.sort(arrayList);
        Map<Integer,String> linkedHashMap = new LinkedHashMap<>();
        for (String  value : arrayList){
            linkedHashMap.put(key,map.get(key));
        }
        System.out.println("sortedMap based on Value using LinkedHashMap : ");
        for (Map.Entry<Integer, String> sortMap:linkedHashMap.entrySet()){
            System.out.println(sortMap.getKey()+" : "+sortMap.getValue());
        }*/
    }

    private static void sortingHashMap1(Map<Integer, String> map) {

        //sorting hashmap
        TreeMap<Integer,String> treeMap = new TreeMap<>(map);

        for(Map.Entry<Integer,String> setItr : treeMap.entrySet()){
            System.out.println(setItr.getKey()+" : "+setItr.getValue());
        }

    }
}