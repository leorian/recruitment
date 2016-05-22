package me.gacl.java.thread;

import java.util.concurrent.*;
import java.util.Random;

/**
 * Created by xiezhonggui on 16-5-22.
 */
public class CompletionServiceTest {

    public static void main(String args[]) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        CompletionService completionService = new ExecutorCompletionService(executor);
        for (int i=1; i<=10; i++){
            final int result = i;
            completionService.submit(new Callable() {
                public Object call() throws Exception {
                    Thread.sleep(new Random().nextInt(5000));
                    return result;
                }
            });
        }
        System.out.println(completionService.take().get());
    }

}
