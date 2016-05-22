package me.gacl.java.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xiezhonggui on 16-5-22.
 */
public class ThreadPoolTest {
    public static void main(String args[]) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i=1; i<=3; i++) {
            final int task = i;
            threadPool.execute(new Runnable() {
                public void run() {
                    System.out.println("Thread Name: " + Thread.currentThread().getName() + "Task Name: " + task);
                }
            });
        }
    }
}
