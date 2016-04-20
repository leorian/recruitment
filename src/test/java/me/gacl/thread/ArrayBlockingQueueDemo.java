package me.gacl.thread;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 两个生产者，一个消费者
 *
 */
public class ArrayBlockingQueueDemo {

    public static void main(String[] args) {
        final ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(3);
        for(int i=0;i<2;i++){
            new Thread(new Runnable() {
                
                public void run() {
                    while(true){
                        try {
                            Thread.sleep(3000);
                            int nextInt = new Random().nextInt(100);
                            System.out.println(Thread.currentThread().getName()+"添加数据为："+nextInt);
                            queue.put(nextInt);//当前操作与take操作是阻塞的
                            System.out.println("当前数据个数"+queue.size());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

        new Thread(new Runnable() {
            
            public void run() {
                while(true){
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName()+"获取数据");
                        Integer take = queue.take();//取数据，如果queue里面没有数据，就会一直等，等queue里面存放数据了，才会执行后续的代码
                        System.out.println("获取到的数据为"+take+" ,当前数据个数"+queue.size());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    
    }

}