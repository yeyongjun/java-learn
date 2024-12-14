package com.learn.distribute;

import cn.hutool.core.thread.ThreadUtil;
import com.learn.distribute.lock.ZookeeperLock;
import com.learn.distribute.lock.ZookeeperLock2;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * @author yeyongjun
 * @since 2024/11/20 16:42
 */
@Slf4j
public class DistributeLockTest {

    public static void main(String[] args) throws Exception{
        System.setProperty("logging.level.com.learn", "INFO");
        zkTest();
    }

    private static void zkTest() throws Exception{
        ZooKeeper zk = new ZooKeeper("172.16.248.132:2181,172.16.248.133:2181,172.16.249.113:2181",
                    3000, event-> System.out.println("receive event" + event));

        // 创建相同的非顺序节点会报错
        String lockNode = zk.create("/locks/sumo", new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        log.info("lockNode is {}", lockNode);
        //会报错
//        lockNode = zk.create("/locks/sumo", new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
//        log.info("lockNode is {}", lockNode);

        // 创建相同的顺序节点不会报错，会递增序号
        lockNode = zk.create("/locks/sumo", new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        log.info("lockNode is {}", lockNode);
        lockNode = zk.create("/locks/sumo", new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        log.info("lockNode is {}", lockNode);
        // 获取子节点
        List<String> list = zk.getChildren("/locks", false);
        log.info("getChildren: {}", list);

        //判断当前节点是临时节点还是持久节点
        String path = "/locks/" + "sumo";
        Stat stat = zk.exists(path, false);
        log.info("path:{}, res: {}", path, stat);
        if (stat != null) {
            long ephemeralOwner = stat.getEphemeralOwner();
            if (ephemeralOwner != 0) {
                System.out.println("This is an ephemeral (temporary) node.");
            } else {
                System.out.println("This is a persistent (permanent) node.");
            }
        } else {
            System.out.println("Node does not exist.");
        }
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
