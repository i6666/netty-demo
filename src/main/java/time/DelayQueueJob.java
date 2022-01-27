package time;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author zhuang.ma
 * @date 2021/9/9
 */
public class DelayQueueJob implements Delayed {
    private String orderId;

    private long timeout;

    public DelayQueueJob(String orderId, long timeout) {
        this.orderId = orderId;
        this.timeout = timeout + System.nanoTime();
    }

    // 返回距离你自定义的超时时间还有多少
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(timeout - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if (this == o)
            return 0;
        DelayQueueJob t = (DelayQueueJob) o;

        long d = ((getDelay(TimeUnit.NANOSECONDS)) - t.getDelay(TimeUnit.NANOSECONDS));

        return (d == 0) ? 0 : ((d < 0) ? -1 : 1);
    }

    void print(){
        System.out.println(orderId + ",订单要取消了=============");
    }

}
