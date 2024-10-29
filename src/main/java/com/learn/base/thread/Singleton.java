package com.learn.base.thread;

/**
 * @author yeyongjun
 * @since 2024/8/7 22:57
 */
public class Singleton {
    private static volatile Singleton singleton;

    public Singleton() {

    }
    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class){
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
