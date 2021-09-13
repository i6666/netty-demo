package hello;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * netty的服务端
 *
 * @author zhuang.ma
 * @date 2021/9/6
 */
public class NettyServer {

    private final int port;

    public NettyServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        NettyServer nettyServer = new NettyServer(8686);
        nettyServer.start();
        System.out.println("服务器关闭");

    }

    private void start() throws InterruptedException {
        System.out.println("NettyServer =====>>>>>start:::::");
        NettyServerHandler nettyServerHandler = new NettyServerHandler();
        /**
         * 线程组
         */
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            //启动服务端
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(eventLoopGroup)//将线程组传入
                    .channel(NioServerSocketChannel.class)//指定使用的NIO进行网络传输（基于NIO选择器的实现，以接受新连接。）
                    .localAddress(new InetSocketAddress(port))
                    /*服务端每接收到一个连接请求，就会新启一个socket通信，也就是channel，
                                       所以下面这段代码的作用就是为这个子channel增加handle*/
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(nettyServerHandler);
                        }
                    });
            ChannelFuture sync = bootstrap.bind().sync();//异步绑定到服务器，sync()会阻塞直到完成
            sync.channel().closeFuture().sync();//阻塞直到服务器的channel关闭
        } finally {
            eventLoopGroup.shutdownGracefully().sync();//优雅关闭线程组
        }
    }

    /**
     * netty 服务端的处理
     */
    static class NettyServerHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf in = (ByteBuf) msg;
            System.out.println("channelRead 客户端读取到数据:" + in.toString(CharsetUtil.UTF_8));
            super.channelRead(ctx, msg);
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            System.out.println("channelRead 客户端读取数据完毕");
            ByteBuf response = Unpooled.copiedBuffer("Response", CharsetUtil.UTF_8);
            ctx.writeAndFlush(response);
            super.channelReadComplete(ctx);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
            super.exceptionCaught(ctx, cause);
        }
    }

}
