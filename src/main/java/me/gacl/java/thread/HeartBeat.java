package me.gacl.java.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by xiezhonggui on 16-5-22.
 */
public class HeartBeat {
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
        Runnable task = new Runnable(){
            public void run() {
                System.out.println("HeartBeat...................");
            }
        };
        executor.scheduleAtFixedRate(task, 5, 3, TimeUnit.SECONDS);
        executor.shutdown();
    }
}
