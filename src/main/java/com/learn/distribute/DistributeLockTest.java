package com.learn.distribute;

import cn.hutool.core.thread.ThreadUtil;
import com.learn.distribute.lock.ZookeeperLock;
import com.learn.distribute.lock.ZookeeperLock2;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yeyongjun
 * @since 2024/11/20 16:42
 */
@Slf4j
public class DistributeLockTest {

    public static void main(String[] args) {
        System.setProperty("logging.level.com.learn", "INFO");
        zookeeperLockTest2();
    }

    private static void zookeeperLockTest() {
        ZookeeperLock zookeeperLock = ZookeeperLock.getInstance();
        String lockKey = "123";
        createThread("thread1", zookeeperLock, lockKey).start();
        createThread("thread2", zookeeperLock, lockKey).start();
        createThread("thread3", zookeeperLock, lockKey).start();
    }

    private static Thread createThread(String name, ZookeeperLock zookeeperLock, String lockKey) {
        return new Thread(()->{
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " 尝试加锁");
            Boolean lockResult = zookeeperLock.acquireDistributedLock(lockKey);
            System.out.println(threadName + " lockResult:" + lockResult);
            ThreadUtil.safeSleep(5000L);
//            zookeeperLock.releaseDistributedLock(lockKey);
//            System.out.println(threadName + " 释放锁");
        }, name);
    }

    private static void zookeeperLockTest2() {
        String lockKey = "123";

        createThread("thread1", lockKey).start();
//        createThread("thread2", lockKey).start();
//        createThread("thread3", lockKey).start();
    }

    private static Thread createThread(String name, String lockKey) {
        return new Thread(()->{
            log.info("尝试加锁");
            ZookeeperLock2 zookeeperLock = new ZookeeperLock2(lockKey);
            zookeeperLock.acquireDistributedLock();
            log.info("加锁成功");
            ThreadUtil.safeSleep(5000L);
        }, name);
    }
}
