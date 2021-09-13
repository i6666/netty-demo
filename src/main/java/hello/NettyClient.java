package hello;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * @author zhuang.ma
 * @date 2021/9/6
 */
public class NettyClient {

    private final int port;
    private final String host;

    public NettyClient(int port, String host) {
        this.port = port;
        this.host = host;
    }

    public static void main(String[] args) throws InterruptedException {
        new NettyClient(8686, "127.0.0.1").start();
    }

    private void start() throws InterruptedException {
        /*线程组*/
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new NettyClientHandler());

            ChannelFuture sync = bootstrap.connect().sync();
            sync.channel().closeFuture().sync();
        } finally {
            eventLoopGroup.shutdownGracefully().sync();
        }


    }

    static class NettyClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
            System.out.println("client acccept:" + msg.toString(CharsetUtil.UTF_8));
        }

        /**
         * 链接建立以后
         *
         * @param ctx
         * @throws Exception
         */
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("channelActive ==========>>>>>>>");
            ByteBuf helloNetty = Unpooled.copiedBuffer("Hello netty", CharsetUtil.UTF_8);
            ctx.writeAndFlush(helloNetty);
            super.channelActive(ctx);
        }
    }

}
