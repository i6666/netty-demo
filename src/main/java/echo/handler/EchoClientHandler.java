package echo.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author zhuang.ma
 * @date 2022/1/7
 */
@Slf4j
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("Client channelActive====");
        ctx.writeAndFlush(Unpooled.copiedBuffer("netty rocks",CharsetUtil.UTF_8));
        super.channelActive(ctx);
    }
    Executor decoderExecutor = ExecutorHelper.createExecutor(2, "decoder");
    Executor ectExecutor = ExecutorHelper.createExecutor(8, "ect");
    Executor senderExecutor = ExecutorHelper.createExecutor(2, "sender");
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {


        log.info("Client received {}====",msg.toString(CharsetUtil.UTF_8));
    }
    private <U> U decoder(ChannelHandlerContext ctx, ByteBuf msg) {
        return null;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
