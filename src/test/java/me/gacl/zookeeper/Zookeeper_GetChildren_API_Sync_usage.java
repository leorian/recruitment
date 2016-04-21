package me.gacl.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by xiezhonggui on 16-4-21.
 */
public class Zookeeper_GetChildren_API_Sync_usage implements Watcher{
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zk = null;
    public static void main(String args[]) {
        String path = "/zk-book";
        try {
            zk = new ZooKeeper("127.0.0.1:2181", 5000, new Zookeeper_GetChildren_API_Sync_usage());
            try {
                connectedSemaphore.await();
                try {
                    zk.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                } catch (KeeperException e) {
                    e.printStackTrace();
                }

                try {
                    zk.create(path + "/c1", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                } catch (KeeperException e) {
                    e.printStackTrace();
                }

                try {
                    List<String> chidrenList = zk.getChildren(path, true);
                    System.out.println(chidrenList);
                    zk.create(path + "/c2", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (KeeperException e) {
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected == event.getState()) {
            if (Event.EventType.None == event.getType() && null == event.getPath()) {
                connectedSemaphore.countDown();
            } else if (event.getType() == Event.EventType.NodeChildrenChanged) {
                try {
                    System.out.println("ReGet Child:" + zk.getChildren(event.getPath(), true));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
