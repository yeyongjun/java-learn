package com.learn.base.collection;

/**
 * @author yeyongjun
 * @since 2024/9/17 14:42
 */
public class HashTest {
    public static void main(String[] args) {
        testHash();
    }
    public static void testHash() {
        String str = "中";
        char[] array = str.toCharArray();
        System.out.println((int) array[0]);
    }
}
