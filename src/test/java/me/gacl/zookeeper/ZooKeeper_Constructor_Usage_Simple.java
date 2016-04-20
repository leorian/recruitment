package me.gacl.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by xiezhonggui on 16-4-20.
 */
public class ZooKeeper_Constructor_Usage_Simple implements Watcher{
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void main(String args[]) {
        try {
            ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000, new ZooKeeper_Constructor_Usage_Simple());
            System.out.println(zooKeeper.getState());
            try {
                connectedSemaphore.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Zookeeper session established.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void process(WatchedEvent event) {
        System.out.println("Receive watched event: " + event);
        if (Event.KeeperState.SyncConnected == event.getState()) {
            connectedSemaphore.countDown();
        }
    }
}
