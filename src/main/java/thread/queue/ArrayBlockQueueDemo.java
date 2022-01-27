package thread.queue;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author zhuang.ma
 * @date 2022/1/25
 */
@Slf4j
public class ArrayBlockQueueDemo {
    public static void main(String[] args) {

        ArrayBlockingQueue queue = new ArrayBlockingQueue<String>(Integer.MAX_VALUE);
        LinkedBlockingQueue<String> strings = new LinkedBlockingQueue<>();

        new Thread(()->{
           while (true){
               try {
                   log.info("取货数据"+queue.poll());
                   Thread.sleep((long) (Math.random() * 1000));
               }catch (Exception e){

               }
           }

        }).start();


        for (int i = 0; i < 6; i++) {
            int no = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        queue.put("塞入数据"+no);
                    } catch (InterruptedException e) {
                        log.error("异常",e);
                        e.printStackTrace();
                    }
                }
            }).start();
        }


    }
}
