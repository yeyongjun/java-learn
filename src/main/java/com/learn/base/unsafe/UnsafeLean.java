package com.learn.base.unsafe;

import sun.misc.Unsafe;

/**
 * @author yeyongjun
 * @since 2024/8/3 16:55
 */
//@Slf4j
public class UnsafeLean {
    public static void main(String[] args) {
        Unsafe unsafe = UnSafeInstance.reflectGetUnsafe();
        memoryTest(unsafe);
    }

    private static void memoryTest(Unsafe unsafe) {
        int size = 4;
        long addr = unsafe.allocateMemory(size);
        long addr3 = unsafe.reallocateMemory(addr, size * 2);
        System.out.println("addr: "+addr);
        System.out.println("addr3: "+addr3);
        try {
            unsafe.setMemory(null,addr ,size,(byte)1);
            for (int i = 0; i < 2; i++) {
                unsafe.copyMemory(null,addr,null,addr3+size*i,4);
            }
            System.out.println(unsafe.getInt(addr));
            System.out.println(unsafe.getLong(addr3));
        }finally {
            unsafe.freeMemory(addr);
            unsafe.freeMemory(addr3);
        }
    }
}
