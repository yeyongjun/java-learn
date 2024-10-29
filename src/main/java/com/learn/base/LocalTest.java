package com.learn.base;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author yeyongjun
 * @since 2024/8/3 16:12
 */
public class LocalTest {

    public static void main(String[] args) throws Exception {
//        List<String> list = new ArrayList<>();
//        String[] a = list.toArray(new String[0]);

//        int[] myArray = {1, 2, 3};
//        List<Object> myList = Arrays.asList(myArray);
//        System.out.println(myList.size());//1
//        System.out.println(myList.get(0));//数组地址值
//        System.out.println(myList.get(1));//报错：ArrayIndexOutOfBoundsException

//        Map<String, String> map = new HashMap<>(5);
//
//
//        Field tableField = HashMap.class.getDeclaredField("table");
//        Field thresholdField = HashMap.class.getDeclaredField("threshold");
//        tableField.setAccessible(true);
//        thresholdField.setAccessible(true);
//        int threshold = (int) thresholdField.get(map);
//
//        Object[] table = (Object[]) tableField.get(map);
//        int capacity = table != null ? table.length : 0;
//        System.out.println(threshold);
//        System.out.println(capacity);
//
//        map.put("1","1");
//        map.put("2","1");
//        map.put("3","1");
//        map.put("4","1");
//        map.put("5","1");
//        map.put("6","1");
//        map.put("7","1");
//
//        threshold = (int) thresholdField.get(map);
//        table = (Object[]) tableField.get(map);
//        capacity = table != null ? table.length : 0;
//        System.out.println(threshold);
//        System.out.println(capacity);

//        List<String> list = new ArrayList<>();
//        Field elementDataField = ArrayList.class.getDeclaredField("elementData");
//        elementDataField.setAccessible(true);
//        Object[] table = (Object[]) elementDataField.get(list);
//        int capacity = table != null ? table.length : 0;
//        System.out.println(capacity);
//        list.add("1");
//        table = (Object[]) elementDataField.get(list);
//        capacity = table != null ? table.length : 0;
//        System.out.println(capacity);
////        11111
////        System.out.println("a".hashCode());
////        System.out.println("b".hashCode());
////        System.out.println("ab".hashCode());
////        System.out.println("abc".hashCode());
//        int[] aaa = new int[5];
//        Arrays.sort(aaa);


        int[] aaa = new int[] {1,2,3};
        for (int i=3; i< aaa.length; i++) {
            System.out.println(1111);
        }
    }
}
