package me.gacl.thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * 创建这个CountDownLatch对象的时候，会传入计数器个数，当前线程调用await方法进行等待其它线程的操作，
 * 当其他线程操作计数器的值直到0的时候，才会继续执行后续操作
 *
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        for(int i=0;i<3;i++){
            new WorkThread(countDownLatch).start();
        }
        System.out.println("老大在这儿等着");
        countDownLatch.await();
        System.out.println("你们都跑完了，该我走人了");
        
    }
    private static class WorkThread extends Thread{
        private CountDownLatch countDownLatch;
        public WorkThread(CountDownLatch countDownLatch){
            this.countDownLatch = countDownLatch;
        }
        @Override
        public void run() {
            System.out.println("执行耗时的操作");
            try {
                Thread.sleep(100*new Random().nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
            System.out.println(Thread.currentThread().getName()+"跑完任务，计数器值改变");
        }
    }

}