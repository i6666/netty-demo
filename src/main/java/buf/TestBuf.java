package buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @author zhuang.ma
 * @date 2022/1/5
 */
public class TestBuf {
    public static void main(String[] args) {

        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        System.out.println(buffer.getClass());
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.heapBuffer();
        System.out.println(byteBuf.getClass());

        System.out.println(buffer);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 200; i++) {
            sb.append("a");
        }

        System.out.println(buffer);

    }
}
