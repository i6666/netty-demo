package time;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;

import java.util.concurrent.TimeUnit;

/**
 * netty 的时间轮
 * @author zhuang.ma
 * @date 2021/9/9
 */
public class HashedWheelTimerTest {

    static class MyTimeTask implements TimerTask{
        boolean flag;

        public MyTimeTask(boolean flag) {
            this.flag = flag;
        }

        @Override
        public void run(Timeout timeout) throws Exception {
            System.out.println("订单自动取消===========");
            this.flag =false;
        }
    }

    public static void main(String[] args) {
       /*  MyTimeTask timeTask = new MyTimeTask(true);
        HashedWheelTimer timer = new HashedWheelTimer();
        timer.newTimeout(timeTask,5, TimeUnit.SECONDS);
       int i = 1;
        while (timeTask.flag){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i +"秒过去了");
            i ++;
        }*/
        String order_no = "18818081400006379200782";
        String substring = order_no.substring(order_no.length() - 3);
        System.out.println(substring);

    }

}
