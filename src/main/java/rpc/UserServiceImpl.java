package rpc;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;


/**
 * @author zhuang.ma
 * @date 2021/12/29
 */
public class UserServiceImpl implements UserService{
    @Override
    public String sayHello(String word) {
        System.out.println("调用成功--参数"+word);
        return "调用成功--参数"+word;
    }

    public static void starServer(String hostName,int port) throws InterruptedException {
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap.group(eventLoopGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringDecoder())
                                .addLast(new StringEncoder())
                                .addLast(new UserServerHandler());

                    }
                });
        bootstrap.bind(port).sync();
    }

    @Override
    public String toString() {
        return "UserServiceImpl{}";
    }
}
