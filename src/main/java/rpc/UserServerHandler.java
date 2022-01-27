package rpc;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author zhuang.ma
 * @date 2021/12/29
 */
public class UserServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String message = msg.toString();
        if (message.equals("UserService")){
            String sayHello = new UserServiceImpl().sayHello(message.substring(message.lastIndexOf('#') + 1));
            ctx.writeAndFlush(sayHello);
        }

    }
}
