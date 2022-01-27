package echo.handler;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author zhuang.ma
 * @date 2022/1/21
 */
public class ExecutorHelper {
    public static Executor createExecutor(int i, String name) {
        ExecutorService executorService = Executors.newFixedThreadPool(i);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        return executorService;
    }
}
