package me.lueguo.simple.littletoy;

import java.util.HashMap;

public class HashMapTest {
    public static void main(String[] args) {
        HashMap<String,Integer> hashMap = new HashMap<>();

        hashMap.put("a",1);
        hashMap.put("b",1);
        hashMap.put("c",1);
        hashMap.put("d",1);
        hashMap.put("e",1);
        hashMap.put("f",1);
        hashMap.put("g",1);

        for (String s: hashMap.keySet()
             ) {
            System.out.println(s + ":" + hashMap.get(s));
        }
    }
}
