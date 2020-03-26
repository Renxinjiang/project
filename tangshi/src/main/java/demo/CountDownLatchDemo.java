package demo;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    private static class Job implements Runnable {
        private CountDownLatch countDownLatch;

        Job(CountDownLatch countDownLatch) {

            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            //模拟工作耗时
            Random random = new Random();
            try {
                Thread.sleep(random.nextInt(30) * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //任务结束了
            countDownLatch.countDown();
            System.out.println("一个线程的任务结束了");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Job(countDownLatch));
            thread.start();
        }

        System.out.println("等待 10 个线程全部结束");
        countDownLatch.await();
        System.out.println("10 个线程全部结束了");
    }
}
