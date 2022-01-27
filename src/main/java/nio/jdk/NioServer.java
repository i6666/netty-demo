package nio.jdk;

import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author zhuang.ma
 * @date 2022/1/27
 */
public class NioServer {
    //负责处理业务操作的线程
    private  static ExecutorService workPool = Executors.newCachedThreadPool();


    abstract class ReactorThread extends Thread{

        Selector selector;

        LinkedBlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();

        private ReactorThread() throws IOException {
            selector= Selector.open();
        }

        boolean running =true;

        @Override
        public void run() {
            while (running){

                Runnable task;

                while ((task = taskQueue.poll())!= null){
                    task.run();
                }

                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    int readyOps = key.readyOps();
                    if ((readyOps & (SelectionKey.OP_READ | SelectionKey.OP_ACCEPT))!= 0|| readyOps == 0){

                        try {
                            SelectableChannel channel = (SelectableChannel)key.attachment();
                            channel.configureBlocking(false);
                            handler(channel);
                            if (!channel.isOpen()){
                                key.cancel();
                            }

                        } catch (Exception e) {
                            key.cancel();
                        }

                    }

                }



















            }


        }

        /**
         * select 监听到有事件后调用此方法
         * @param channel
         * @throws Exception
         */
        public abstract void handler(SelectableChannel channel) throws Exception;

    }

    private ServerSocketChannel serverSocketChannel;

    private ReactorThread[] mainReactorThreads = new ReactorThread[1];
    private ReactorThread[] subReactorThreads = new ReactorThread[8];





    public static void main(String[] args) {


    }

}
