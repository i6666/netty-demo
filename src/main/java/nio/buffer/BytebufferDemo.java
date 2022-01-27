package nio.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhuang.ma
 * @date 2022/1/27
 */
@Slf4j
public class BytebufferDemo {
    public static void main(String[] args) {

        ByteBuf buffer = Unpooled.buffer(6);
        byte[] bytes = {1,2,3,4,5,6};

        buffer.writeBytes(bytes);

        log.info("写入6个 {},{}",buffer,buffer.array());
        buffer.readByte();
        log.info("读取一个 {},{}",buffer,buffer.array());
        buffer.readByte();
        log.info("读取2个 {},{}",buffer,buffer.array());
        buffer.discardReadBytes();
        log.info("release {},{}",buffer,buffer.array());

        byte[] bytes2 = {1,2,3,4,5,6,7,8,9,10};
        buffer.writeBytes(bytes2);
        log.info("写超过限制 {},{}",buffer,buffer.array());


        ByteBuf directByteBuf = Unpooled.directBuffer(10);
        log.info("写超过限制 {},{}",directByteBuf,directByteBuf.array());

    }

}
