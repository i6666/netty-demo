package thread.future;

import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

/**
 * @author zhuang.ma
 * @date 2022/1/25
 */
public class MyFutureTask<T> implements Runnable, Future {

    private Callable<T> callable;

    private volatile int status;

    private T result;

    public MyFutureTask(Callable<T> callable) {
        this.callable = callable;
    }

    LinkedBlockingQueue<Thread> waits = new LinkedBlockingQueue<>();

    @Override
    public void run() {
        try {
            result = callable.call();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            status = 1;
        }

        //唤醒等待线程
        Thread wait = waits.poll();
        while (wait != null) {
            LockSupport.unpark(wait);
            //重新赋值，获取下一个
            wait = waits.poll();
        }


    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return status == 1;
    }

    @Override
    public T get() throws InterruptedException, ExecutionException {
        if (status == 1) {
            return result;
        }
        // 加入等待队列
        waits.offer(Thread.currentThread());

        while (1 != status) {
            LockSupport.park();
        }

        return result;
    }

    @Override
    public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }
}
