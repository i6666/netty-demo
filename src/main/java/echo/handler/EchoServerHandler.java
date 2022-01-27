package echo.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhuang.ma
 * @date 2022/1/7
 */
@Slf4j
@ChannelHandler.Sharable //可以被多个channel 安全共享    can be added to one or more {@link ChannelPipeline}s multiple times
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf in = (ByteBuf) msg;
        log.info("Server received:{}",in.toString(CharsetUtil.UTF_8));
        ctx.write(in); //将接受到的消息 写给发送者，不冲刷出站
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx)  {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
        .addListener(ChannelFutureListener.CLOSE);  //将消息冲刷，然后关闭channel
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        cause.printStackTrace();
        ctx.close();

    }
}
