package wiki;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author zhuang.ma
 * @date 2021/12/29
 */
public class TimeDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("decode================");
        if (in.readableBytes() < 4){
            return;
        }
        UnixTime unixTime = new UnixTime(in.readUnsignedInt());
        out.add(unixTime);
    }
}
