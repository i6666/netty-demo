package nio.select;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author zhuang.ma
 * @date 2021/9/10
 */
public class NioService {

    static List<SocketChannel> channelList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        // 创建NIO serverSocketChannel 与BIO 的serverSocket 类似
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.socket().bind(new InetSocketAddress(8888));
        //设置serverSocketChannel 为非阻塞
        serverSocketChannel.configureBlocking(false);
        System.out.println("服务启动成功");

        while (true){
            // 非阻塞模式 accept方法不会阻塞
            // NIO 的非阻塞是由系统内部实现的，底层调用的是Linux 的accept 函数
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel != null){
                System.out.println("连接成功");
                socketChannel.configureBlocking(false);
                // 保存客户端连接到list 中
                channelList.add(socketChannel);
            }

            Iterator<SocketChannel> iterator = channelList.iterator();
            while (iterator.hasNext()){
                SocketChannel sc = iterator.next();
                ByteBuffer byteBuffer = ByteBuffer.allocate(128);
                int len = sc.read(byteBuffer);
                if (len>0){
                    System.out.println("接收到消息"+ new String(byteBuffer.array()));
                }else if(len == -1){
                    iterator.remove();
                }
            }
        }

    }
}
