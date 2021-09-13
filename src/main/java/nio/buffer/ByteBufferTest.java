package nio.buffer;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * Created by zhuang.ma on 2020/12/22.
 */
public class ByteBufferTest {
    public static void main(String[] args) {

        String msg = "netty demo 嘿嘿嘿";

        ByteBuffer allocate = ByteBuffer.allocate(1024);

        byte[] bytes = msg.getBytes();

        ByteBuffer msgBuffer = allocate.put(bytes);
        //切换成读模式
        Buffer flip = msgBuffer.flip();
        byte[] tempByte  = new byte[bytes.length];
        int i =0 ;
        while (flip.hasRemaining()){
            tempByte[i] = msgBuffer.get();
            i++;
        }
        System.out.println(new String(tempByte));

    }
}
