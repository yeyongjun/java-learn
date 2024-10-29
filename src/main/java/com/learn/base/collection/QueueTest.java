package com.learn.base.collection;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author yeyongjun
 * @since 2024/8/11 16:49
 */
public class QueueTest {

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);
        queue.add(1);
        queue.put(1);
        queue.offer(1);
        queue.poll();

        Thread thread = new Thread(()->{});
        thread.join();
    }

}
