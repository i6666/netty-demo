package hello;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author zhuang.ma
 * @date 2021/12/30
 */
@Slf4j
public class TestJavaFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        Future<Integer> future = threadPool.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.info("====call");
                Thread.sleep(1000);
                return 50;
            }
        });
        log.info("===="+future.isDone());
        Integer integer = future.get();
        log.info("===="+integer);
    }

}
