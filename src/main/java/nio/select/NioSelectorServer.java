package nio.select;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author zhuang.ma
 * @date 2021/9/10
 */
public class NioSelectorServer {

    public static void main(String[] args) throws IOException {
        // 创建NIO serverSocketChannel 与BIO 的serverSocket 类似
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.socket().bind(new InetSocketAddress(8888));
        //设置serverSocketChannel 为非阻塞
        serverSocketChannel.configureBlocking(false);
        //把ServerSocketChannel 注册到selector 上，并且selector 对客户端accept 连接操作感兴趣。
        Selector selector = Selector.open();
        SelectionKey register = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务启动");
        while (true){
            //阻塞等待事件发生
            int select = selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()){
                    ServerSocketChannel server = (ServerSocketChannel)selectionKey.channel();
                    SocketChannel socketChannel = server.accept();
                    System.out.println("连接成功");
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                }else if (selectionKey.isReadable()){
                    SocketChannel sc = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(128);
                    int len = sc.read(byteBuffer);
                    if (len>0){
                        System.out.println("接收到消息"+ new String(byteBuffer.array()));
                    }else if(len == -1){
                        System.out.println("客户端断开连接");
                        sc.close();
                    }
                }
                iterator.remove();
            }


        }


    }



}
