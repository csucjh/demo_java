package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程池线程是否后台线程.
 * <p>
 * 非后台线程
 */
public class PoolThreadIsDeamon {

    private static ExecutorService service = Executors.newCachedThreadPool();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("main start...");
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(15 * 1000);
                    System.out.println("child out...isDaemon:" + Thread.currentThread().isDaemon());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        service.shutdown();

        if (!service.awaitTermination(10, TimeUnit.SECONDS)) {
            service.shutdownNow();
            System.out.println("shutdownNow");
        }
        System.out.println("main end...");
    }
}
