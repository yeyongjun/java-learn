package com.learn.distribute.lock;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author yeyongjun
 * @since 2024/11/20 16:02
 */
@Slf4j
public class ZookeeperLock {
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private ZooKeeper zookeeper;
    private Object lock;
    private long waitTime = 1000L;

    public ZookeeperLock() {
        try {
            this.zookeeper = new ZooKeeper("172.16.248.132:2181,172.16.248.133:2181,172.16.249.113:2181",
                    3000, new ZooKeeperWatcher());
            try {
                connectedSemaphore.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ZooKeeper session established......");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取分布式锁
     *
     * @param productId
     */
    public Boolean acquireDistributedLock(String productId) {
        String path = "/product-lock-" + productId;
        try {
            zookeeper.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            return true;
        } catch (Exception e) {
            while (true) {
                try {
                    // 相当于是给node注册一个监听器，去看看这个监听器是否存在
                    Stat stat = zookeeper.exists(path, true);
                    if (stat != null) {
                        synchronized (this) {
                            this.wait(waitTime);
                        }
                    }
                    zookeeper.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                    return true;
                } catch (Exception ee) {
                    continue;
                }
            }
        }
    }

    /**
     * 释放掉一个分布式锁 *
     *
     * @param productId
     */
    public void releaseDistributedLock(String productId) {
        String path = "/product-lock-" + productId;
        try {
            zookeeper.delete(path, -1);
            System.out.println("release the lock for product[id=" + productId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class ZooKeeperWatcher implements Watcher {
        public void process(WatchedEvent event) {
            System.out.println("Receive watched event: " + event.getState());
            if (Event.KeeperState.SyncConnected == event.getState()) {
                connectedSemaphore.countDown();
            }
            synchronized (this) {
                this.notifyAll(); // 唤醒阻塞线程
            }
        }
    }

    /**
     * 封装单例的静态内部类
     */
    private static class Singleton {
        private static ZookeeperLock instance;

        static {
            instance = new ZookeeperLock();
        }

        public static ZookeeperLock getInstance() {
            return instance;
        }
    }

    /**
     * 获取单例 *
     *
     * @return
     */
    public static ZookeeperLock getInstance() {
        return Singleton.getInstance();
    }

    /**
     * 初始化单例的便捷方法
     */
    public static void init() {
        getInstance();
    }
}

