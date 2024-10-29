package com.learn.base.thread;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author yeyongjun
 * @since 2024/8/6 21:59
 */
public class Atomic {
    static AtomicStampedReference<String> asr = new AtomicStampedReference<>("SnailClimb", 1);

    public static void main(String[] args) {
        // 打印初始值和版本号
        int[] initialStamp = new int[1];
        System.out.println(Arrays.toString(initialStamp));
        String initialRef = asr.get(initialStamp);
        System.out.println(Arrays.toString(initialStamp));
    }
}
