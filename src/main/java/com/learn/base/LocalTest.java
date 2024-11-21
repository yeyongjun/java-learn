package com.learn.base;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author yeyongjun
 * @since 2024/8/3 16:12
 */
public class LocalTest {

    public static void main(String[] args) throws Exception {
//        int a = 0;
//        double b = Math.sqrt(2147483600);
//        int c = (int)Math.floor(b);
//        System.out.println(b);
//        System.out.println(c);
//        System.out.println( 46341* 46341 - 2147483600);
//        System.out.println( 7060* 7060 + 45800 * 45800);
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


//        int[] aaa = new int[] {1,2,3};
//        for (int i=3; i< aaa.length; i++) {
//            System.out.println(1111);
//        }
//        System.out.println(judgeSquareSum(2147483600));
//        LinkedList<Character> list = new LinkedList<>();
//        list.removeFirst();
//        System.out.println(Integer.MAX_VALUE);
//        Set<Character> set = new HashSet<>();
//        set.add('b');
//        set.add('c');
//        set.add('a');
//        set.forEach(x-> System.out.println(x));
//        System.out.println(findMedianSortedArrays(new int[]{1,2}, new int[]{3,4}));
//        System.out.println("babad".substring(0,5));
//        char[][] array = new char[1][1];
//        System.out.println(array[0][0] == '\0');
//        System.out.println(longestPalindrome("cbbd"));

        System.out.println(convert("A", 2));
    }

    public static String convert(String s, int numRows) {
//        s.length() / (numRows * 3 - 2) * numRows
        char[][] array = new char[numRows][s.length()];
        int i = 0,row = -1, column = 0;
        while (i<s.length()) {
            int c = column % (numRows-1);
            if (c == 0) {
                row = -1;
                while (row < numRows-1 && i<s.length()) {
                    array[++row][column] = s.charAt(i++);
                }
                column++;
            }
            if (c > 0 && row > 0) {
                array[--row][column++] = s.charAt(i++);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int j =0; j < numRows; j++) {
            for (int k = 0; k < column; k++) {
                if (array[j][k] != '\0') {
                    sb.append(array[j][k]);
                    System.out.print(array[j][k]);
                }else {
                    System.out.print("-");
                }

            }
            System.out.println();
        }
        return sb.toString();
    }

    public static String longestPalindrome(String s) {
        String res = "";
        for (int i=0; i< s.length(); i++) {
            int j=i+1;
            while (j<s.length() && s.charAt(i) == s.charAt(j)) {
                j++;
            }
            if (res.length() < j-i) {
                res = s.substring(i, j);
            }
            int left = i-1;
            int right = i+1;
            while (left>=0 && right<s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            if (res.length() < (right - left+1)) {
                res = s.substring(left+1, right);
            }
        }
        return res;
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] mergeArray = new int[nums1.length + nums2.length];
        int i=0, j=0, k=0;
        while (i< nums1.length && j< nums2.length) {
            if (nums1[i] > nums2[j]) {
                mergeArray[k++] = nums2[j++];
            }else {
                mergeArray[k++] = nums1[i++];
            }
        }
        while (i< nums1.length) {
            mergeArray[k++] = nums1[i++];
        }
        while (j< nums2.length) {
            mergeArray[k++] = nums2[j++];
        }
        int mid = k/2;
        if (k % 2 > 0) {
            return mergeArray[mid];
        }
        return (double)(mergeArray[mid] + mergeArray[mid-1])/2;
    }

    public static boolean judgeSquareSum(int c) {
        int a = 0;
        int b = (int)Math.sqrt(c);
        while (a <= b) {
            int tmp = a * a + b * b;
            if (tmp == c) {
                return true;
            }
            if (tmp < c) {
                a++;
            }else {
                b--;
            }
        }
        return false;
    }
}
