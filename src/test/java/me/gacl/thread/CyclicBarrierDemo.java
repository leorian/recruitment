package me.gacl.thread;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * CyclicBarrier的使用
 *
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        final int threadNum = 5;
        ExecutorService threadPool = Executors.newCachedThreadPool();
        final CyclicBarrier cb = new CyclicBarrier(threadNum);
        for(int i=0;i<threadNum;i++){
            threadPool.execute(new Runnable(){

                public void run() {
                    try {
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("线程"
                                + Thread.currentThread().getName()
                                + "运行到Barrier1，已有"+ (cb.getNumberWaiting() + 1)+ "个已经到达，"
                                + (cb.getNumberWaiting() == threadNum-1 ? "都到齐了，继续走啊"
                                        : "正在等候"));
                        cb.await();//障碍点1：当前线程在await这个障碍地点停顿，等着其它线程运行到这
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("线程"
                                + Thread.currentThread().getName()
                                + "运行到Barrier2，已有"+ (cb.getNumberWaiting() + 1)+ "个已经到达，"
                                + (cb.getNumberWaiting() == threadNum-1 ? "都到齐了，继续走啊"
                                        : "正在等候"));
                        cb.await();//障碍点2：
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("线程"
                                + Thread.currentThread().getName()
                                + "运行到Barrier3，已有"+ (cb.getNumberWaiting() + 1)+ "个已经到达，"
                                + (cb.getNumberWaiting() == threadNum-1 ? "都到齐了，继续走啊"
                                        : "正在等候"));
                        cb.await();//障碍点3：
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                
            });
        }
        //正在执行的任务接着执行，后续不允许添加任务
        threadPool.shutdown();
    }

}