package demo;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class JudgeFinish {
    private static int COUNT = 10;
    /*
    private static int successCount = 0;
    private static int failureCount = 0;
     */
    // AtomicInteger原子类，因为后序的自增不是线程安全的。
    private static AtomicInteger successCount = new AtomicInteger(0);
    private static AtomicInteger failureCount = new AtomicInteger(0);

    private static class Job implements Runnable {
        private void work() throws IOException, InterruptedException {
            Random random = new Random();
            int n = random.nextInt(5);
            if (n < 2) {
                throw new IOException();
            }
            Thread.sleep(5);
        }

        @Override
        public void run() {
            try {
                work();
                successCount.getAndIncrement();
            } catch (IOException | InterruptedException e) {
                failureCount.getAndIncrement();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < COUNT; i++) {
            Thread thread = new Thread(new Job());
            thread.start();
        }

        while (successCount.get() + failureCount.get() != COUNT) {
            Thread.sleep(1000);
            System.out.println("任务没有全部完成");
        }
        System.out.println("任务全部完成");
    }
}
