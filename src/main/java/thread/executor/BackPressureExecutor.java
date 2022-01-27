package thread.executor;

import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 反压执行器
 * @author zhuang.ma
 * @date 2022/1/24
 */
public class BackPressureExecutor implements Executor {

    private final List<ExecutorService> executors;

    private String name ;
    private int executorNumber;
    private int maxSize;
    private int capacity;
    private long rejectSleepMills = 1l;

    public BackPressureExecutor(String name, int executorNumber, int coreSize,int maxSize, int capacity,long rejectSleepMills) {
        this.rejectSleepMills = rejectSleepMills;
        this.executors = new ArrayList<>(executorNumber);

        for (int i = 0; i < executorNumber; i++) {
            ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(capacity);
            this.executors.add(new ThreadPoolExecutor(coreSize, maxSize, 0L, TimeUnit.MILLISECONDS, queue,
                    new DefaultThreadFactory(name + "-" + i + "-%d"),new ThreadPoolExecutor.AbortPolicy()
            ));
        }



    }


    @Override
    public void execute(Runnable command) {

    }
}
