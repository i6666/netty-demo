package thread.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author zhuang.ma
 * @date 2022/1/25
 */
@Slf4j
public class FutureDemo {

    static ExecutorService executorService = Executors.newFixedThreadPool(10);


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        MyFutureTask futureTask = new MyFutureTask<>(() -> {
            Thread.sleep(100L);
            return "gogo";
        });

       // executorService.submit(futureTask);

        new Thread(futureTask).start();
        new Thread(futureTask).start();

        log.info("result {}",futureTask.get());

    }


}
