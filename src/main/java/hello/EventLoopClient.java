package hello;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhuang.ma
 * @date 2021/12/30
 */
@Slf4j
public class EventLoopClient {
    public static void main(String[] args) throws InterruptedException {
        Bootstrap b = new Bootstrap();
        ChannelFuture future = b.group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                })
                .connect("localhost", 8081);
        //ChannelFuture sync = future.sync(); //使用sync 同步结果
//        Channel channel = future.channel();
//        channel.writeAndFlush("hell");
        System.out.println(future);
        System.out.println("");

        ChannelFuture channelFuture = future.addListener(new ChannelFutureListener(){

            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                Channel channel = future.channel();
                log.info(String.valueOf(channel));
                channel.writeAndFlush("hell");
            }
        });

    }


}
