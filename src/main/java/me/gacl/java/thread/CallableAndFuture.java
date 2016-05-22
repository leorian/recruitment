package me.gacl.java.thread;

import java.util.concurrent.*;

/**
 * Created by xiezhonggui on 16-5-22.
 */
public class CallableAndFuture {
    public static void main(String args[]) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new Callable<String>() {
            public String call() throws Exception {
                return "MOBIN";
            }
        });
        System.out.println("Task Result: " + future.get());
        executor.shutdown();
    }
}
