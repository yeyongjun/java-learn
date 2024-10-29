package com.learn.base.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author yeyongjun
 * @since 2024/8/4 11:18
 */
public class ObjectOperate {

    private final int value = 1;

    public static void main(String[] args) throws Exception{
//        objectModify();
//        objectInstance();
//        "10000000 00000000 00000000 00000111"

        System.out.println(Integer.toBinaryString(-128));
        System.out.println(Integer.toBinaryString(127));
        System.out.println(Integer.toBinaryString(-7>>1));

    }

    private static void objectModify() throws Exception{
        Unsafe unsafe = reflectGetUnsafe();
        assert unsafe != null;
        long offset = unsafe.objectFieldOffset(ObjectOperate.class.getDeclaredField("value"));
        System.out.println("offset:" + offset);
        ObjectOperate main = new ObjectOperate();
        System.out.println("value before putInt: " + main.value);
        unsafe.putInt(main, offset, 42);
        System.out.println("value after putInt: " + main.value);
        System.out.println("value after putInt: " + unsafe.getInt(main, offset));
    }

    private static void objectInstance() throws Exception{
        Unsafe unsafe = reflectGetUnsafe();
        ObjectOperate modify = (ObjectOperate) unsafe.allocateInstance(ObjectOperate.class);
        System.out.println("instance value:" + modify.value);
    }

    private static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
