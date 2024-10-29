package com.learn.base.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author yeyongjun
 * @since 2024/8/4 12:27
 */
public class UnSafeInstance {

    public static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
