package thread.queue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author zhuang.ma
 * @date 2022/1/25
 */
public class Message implements Delayed {
    String content;
    Long sendTime;
    @Override
    public long getDelay(TimeUnit unit) {
        long delay = sendTime - System.currentTimeMillis();
        return TimeUnit.NANOSECONDS.convert(delay,TimeUnit.NANOSECONDS);
    }

    public Message(String content, Long sendTime) {
        this.content = content;
        this.sendTime = sendTime;
    }

    @Override
    public int compareTo(Delayed o) {
        return o.getDelay(TimeUnit.NANOSECONDS)>this.getDelay(TimeUnit.NANOSECONDS)?1:-1;
    }
}
