package nio.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhuang.ma
 * @date 2022/1/27
 */
@Slf4j
public class ZeroCopyDemo {
    public static void main(String[] args) {
        byte[] bytes = {1,2,3,4,5,6};
        ByteBuf buffer = Unpooled.wrappedBuffer(bytes);
        log.info("{},{}",buffer.getByte(5),buffer);
        bytes[5] = 22;
        log.info("{},{}",buffer.getByte(5),buffer);

        CompositeByteBuf compositeByteBuf = Unpooled.compositeBuffer();
        compositeByteBuf.addComponents(buffer,buffer);

        log.info("{}",compositeByteBuf);

    }
}
