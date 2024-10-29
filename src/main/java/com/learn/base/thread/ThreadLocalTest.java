package com.learn.base.thread;

/**
 * @author yeyongjun
 * @since 2024/8/6 22:36
 */
public class ThreadLocalTest {
    static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set("123");
    }
}
