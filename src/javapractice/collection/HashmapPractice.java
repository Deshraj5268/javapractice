package javapractice.collection;

import java.util.HashMap;
import java.util.Map;

public class HashmapPractice {

    public static void main(String[] args) {

        Map<Integer,String> map = new HashMap<>();
        map.put(1,"thakur");
        map.put(2,"deshraj");
        map.put(3,"singh");

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
}