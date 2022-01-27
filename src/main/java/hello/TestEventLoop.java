package hello;

import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.NettyRuntime;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author zhuang.ma
 * @date 2021/12/30
 */
@Slf4j
public class TestEventLoop {
    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup(2);//io 普通 定时
        System.out.println(group.next());
        System.out.println(group.next());
        System.out.println(group.next());
        System.out.println(group.next());


        /*group.next().submit(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("ok");
        });
        log.info("main");*/

        group.next().scheduleAtFixedRate(()->{
            System.out.println("ok");
        },0,1, TimeUnit.SECONDS);

    }
}
