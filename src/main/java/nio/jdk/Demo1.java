package nio.jdk;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author zhuang.ma
 * @date 2022/1/27
 */
public class Demo1 {
    public static void main(String[] args) throws IOException {

        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        channel.connect(new InetSocketAddress("",80));
        ByteBuffer[] buffers = new ByteBuffer[5];
        channel.write(buffers);

        long read = channel.read(buffers);

        channel.close();


        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        System.out.println("启动成功");

        while (true){
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel != null){
                SocketAddress remoteAddress = socketChannel.getRemoteAddress();


            }
        }


    }
}
