package thread;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author zhuang.ma
 * @date 2022/1/24
 */
@Slf4j
public class SemaphoreDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(8);

        Semaphore semaphore = new Semaphore(5);

        for (int i = 0; i < 10; i++) {
            int no = i;
            Runnable runnable = new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    semaphore.acquire();
                   log.info("get success " + no);
                    Thread.sleep((long) (Math.random() * 1000));
                    semaphore.release();
                    log.info("semaphore num ==== " + semaphore.availablePermits());
                }
            };
            executorService.submit(runnable);
        }

    }
}
