package hello;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * @author zhuang.ma
 * @date 2021/12/30
 */
@Slf4j
public class CloseFutureClient {

    public static void main(String[] args) throws InterruptedException {

        Bootstrap b = new Bootstrap();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        ChannelFuture future = b.group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast("",new LoggingHandler(LogLevel.DEBUG))
                                .addLast(new StringEncoder());
                    }
                }).connect("localhost", 8081);
        Channel channel = future.sync().channel();
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String line = scanner.nextLine();
                if ("q".equals(line)) {
                    //同步方式关闭
                    try {
                        /* channel.closeFuture().sync();*/
                        channel.closeFuture().addListener(new ChannelFutureListener() {
                            @Override
                            public void operationComplete(ChannelFuture future) {
                                System.out.println("关闭之后的操作");
                                workerGroup.shutdownGracefully();
                            }
                        });

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                } else {
                    channel.writeAndFlush(line);
                }
            }
        }).start();


    }

}
