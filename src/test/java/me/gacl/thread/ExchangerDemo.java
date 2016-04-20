package me.gacl.thread;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.Exchanger;

/**
 * 两个线程之间的数据交换
 *
 */
public class ExchangerDemo {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<String>();//通过这玩意儿来交换数据
//        ExecutorService threadPool = Executors.newCachedThreadPool();
//        threadPool.execute(command);
        new ThreadA(exchanger).start();
        new ThreadB(exchanger).start();
    }
    private static class ThreadA extends Thread{
        private Exchanger<String> exchanger;
        public ThreadA(Exchanger<String> exchanger){
            this.exchanger = exchanger;
        }
        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName()+"执行之前"+new Date().toLocaleString());
                Thread.sleep(new Random().nextInt(100)*100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println(Thread.currentThread().getName()+"开始交换数据"+new Date().toLocaleString());
                String exchange = exchanger.exchange("A的数据");//给我等着，直到需要交换数据的线程B到来
                System.out.println(Thread.currentThread().getName()+"交换后得到的数据:"+exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
    }
    private static class ThreadB extends Thread{
        private Exchanger<String> exchanger;
        public ThreadB(Exchanger<String> exchanger){
            this.exchanger = exchanger;
        }
        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName()+"执行之前"+new Date().toLocaleString());
                Thread.sleep(new Random().nextInt(100)*100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println(Thread.currentThread().getName()+"开始交换数据"+new Date().toLocaleString());
                String exchange = exchanger.exchange("B的数据");
                System.out.println(Thread.currentThread().getName()+"交换后得到的数据:"+exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
    }

}