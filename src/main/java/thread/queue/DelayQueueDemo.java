package thread.queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;

/**
 * @author zhuang.ma
 * @date 2022/1/25
 */
public class DelayQueueDemo {
    public static void main(String[] args) throws InterruptedException {

        DelayQueue<Delayed> delayeds = new DelayQueue<>();
        delayeds.add(new Message("a",System.currentTimeMillis() + 5000));

        while (true){
            System.out.println(delayeds.poll());
            Thread.sleep(1000);
        }

    }
}
