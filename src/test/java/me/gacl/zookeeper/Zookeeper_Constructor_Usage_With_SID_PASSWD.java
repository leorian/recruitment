package me.gacl.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by xiezhonggui on 16-4-20.
 */
public class Zookeeper_Constructor_Usage_With_SID_PASSWD implements Watcher{
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    public static void main(String args[]) {
        try {
            ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000, new Zookeeper_Constructor_Usage_With_SID_PASSWD());
            System.out.println("No.1-1: " + zooKeeper.getState());
            try {
                connectedSemaphore.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("No.1-2: " + zooKeeper.getState());
            long sessionId = zooKeeper.getSessionId();
            byte[] passwd = zooKeeper.getSessionPasswd();

            //Use illegal sessionId and sessionPassWd
            zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000, new Zookeeper_Constructor_Usage_With_SID_PASSWD(),
                    1l, "test".getBytes());
            System.out.println("No.2: " + zooKeeper.getState());

            //Use correct sessionId and sessionPassWd
            zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000, new Zookeeper_Constructor_Usage_With_SID_PASSWD(),
                    sessionId, passwd);
            System.out.println("No.3: " + zooKeeper.getState());

            try {
                Thread.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

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
