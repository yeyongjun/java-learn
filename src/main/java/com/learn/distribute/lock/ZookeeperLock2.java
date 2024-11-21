package com.learn.distribute.lock;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author yeyongjun
 * @since 2024/11/20 17:02
 */
@Slf4j
public class ZookeeperLock2 {

    private ZooKeeper zk;
    private String locksRoot = "/locks";
    private String productId;
    private String waitNode;
    private String lockNode;
    private CountDownLatch latch;
    private CountDownLatch connectedLatch = new CountDownLatch(1);
    ZooKeeperWatcher watcher = new ZooKeeperWatcher();

    public ZookeeperLock2(String productId) {
        this.productId = productId;
        try {
            String address = "172.16.248.132:2181,172.16.248.133:2181,172.16.249.113:2181";
            zk = new ZooKeeper(address, 3000, watcher);
            connectedLatch.await();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private class ZooKeeperWatcher implements Watcher {
        public void process(WatchedEvent event) {
            log.info("Receive watched event: {}" + event.getState());
            if (Event.KeeperState.SyncConnected == event.getState()) {
                connectedLatch.countDown();
            }
            if (latch != null) {
                latch.countDown();
            }
        }
    }

    public void acquireDistributedLock() {
        try {
            if (this.tryLock()) {

            } else {
                waitForLock(waitNode, 30000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean tryLock() {
        try {
            // 传入进去的locksRoot + “/” + productId
            // 假设productId代表了一个商品id，比如说1
            // locksRoot = locks
            // /locks/10000000000，/locks/10000000001，/locks/10000000002
            lockNode = zk.create(locksRoot + "/" + productId, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            // 看看刚创建的节点是不是最小的节点
            // locks:10000000000，10000000001，10000000002
            log.info("lockNode is {}", lockNode);
            List<String> locks = zk.getChildren(locksRoot, false);
            log.info("locks are {}", locks.toString());
            Collections.sort(locks);
            if (lockNode.equals(locksRoot + "/" + locks.get(0))) { //如果是最小的节点,则表示取得锁
                return true;
            }
            //如果不是最小的节点，找到比自己小1的节点
            int previousLockIndex = -1;
            for (int i = 0; i < locks.size(); i++) {
                if (lockNode.equals(locksRoot + "/" + locks.get(i))) {
                    previousLockIndex = i - 1;
                    break;
                }
            }
            this.waitNode = locks.get(previousLockIndex);
            log.info("wait for :{}", waitNode);
        } catch (Exception e) {
            log.info("获取锁失败");
//            throw new RuntimeException(e);
        }
        return false;
    }

    private boolean waitForLock(String waitNode, long waitTime) throws InterruptedException, KeeperException {

        Stat stat = zk.exists(locksRoot + "/" + waitNode, true);
        if (stat != null) {
            this.latch = new CountDownLatch(1);
            this.latch.await(waitTime, TimeUnit.MILLISECONDS);
            this.latch = null;
        }
        return true;
    }

    public void unlock() {
        try {
            System.out.println("unlock " + lockNode);
            zk.delete(lockNode, -1);
            lockNode = null;
            zk.close();
        } catch (InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
    }

}
