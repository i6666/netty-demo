package hello;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhuang.ma
 * @date 2022/1/5
 */
@Slf4j
public class TestServicePipeline {
    public static void main(String[] args) {

        new ServerBootstrap().group(new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("handle1",new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                log.info("----------1");
                                String name = ((ByteBuf) msg).toString(CharsetUtil.UTF_8);
                                super.channelRead(ctx, name);
                            }
                        });
                        pipeline.addLast("handle2",new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                Student student = new Student(msg.toString());
                                log.info("----------2{}",student);
                                super.channelRead(ctx, student);
                            }
                        });
                        pipeline.addLast("handle3",new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                log.info("----------3{}",msg.getClass());
                                ch.writeAndFlush(ctx.alloc().buffer().writeBytes("server....".getBytes()));
                                super.channelRead(ctx, msg);
                            }
                        });
                        pipeline.addLast("handle4",new ChannelOutboundHandlerAdapter(){
                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                log.info("----------4");
                                super.write(ctx, msg, promise);
                            }
                        });
                        pipeline.addLast("handle5",new ChannelOutboundHandlerAdapter(){
                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                log.info("----------5");
                                super.write(ctx, msg, promise);
                            }
                        });
                        pipeline.addLast("handle6",new ChannelOutboundHandlerAdapter(){
                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                log.info("----------6");
                                super.write(ctx, msg, promise);
                            }
                        });
                    }
                }).bind(8081);


    }
}
