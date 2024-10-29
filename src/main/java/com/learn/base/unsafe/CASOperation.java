package com.learn.base.unsafe;

import sun.misc.Unsafe;

/**
 * @author yeyongjun
 * @since 2024/8/4 12:25
 */
public class CASOperation {

    private volatile int a;
    public static void main(String[] args){
        CASOperation casTest= new CASOperation();
        new Thread(()->{
            for (int i = 1; i < 5; i++) {
                casTest.increment(i);
                System.out.println("thread1 " + casTest.a+" ");
            }
        }).start();
        new Thread(()->{
            for (int i = 5 ; i <10 ; i++) {
                casTest.increment(i);
                System.out.println("thread2 " + casTest.a+" ");
            }
        }).start();
    }

    private void increment(int x){
        Unsafe unsafe = UnSafeInstance.reflectGetUnsafe();
        while (true){
            try {
                long fieldOffset = unsafe.objectFieldOffset(CASOperation.class.getDeclaredField("a"));
                if (unsafe.compareAndSwapInt(this,fieldOffset,x-1,x))
                    break;
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }
}
