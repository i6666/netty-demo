package nio.channel;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by zhuang.ma on 2020/12/22.
 */
public class FileChannelTest {
    public static void main(String[] args) throws IOException {
        //demo1();

        //demo2();
        File file = new File("a.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel inputChannel = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream(new File("b.txt"));
        FileChannel outputChannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());

        outputChannel.transferFrom(inputChannel,0,byteBuffer.limit());

        //关闭通道
        fileInputStream.close();
        fileOutputStream.close();
        outputChannel.close();
        inputChannel.close();

        return;

    }

    private static void demo2() throws IOException {
        File file = new File("a.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel inputChannel = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream(new File("b.txt"));
        FileChannel outputChannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());

        inputChannel.transferTo(0,byteBuffer.limit(),outputChannel);

        //关闭通道
        fileInputStream.close();
        fileOutputStream.close();
        outputChannel.close();
        inputChannel.close();
    }

    private static void demo1() throws IOException {
        File file = new File("a.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel inputChannel = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream(new File("b.txt"));
        FileChannel outputChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        inputChannel.read(byteBuffer);
        //切换成读模式
        byteBuffer.flip();
        outputChannel.write(byteBuffer);
        //关闭通道
        fileInputStream.close();
        fileOutputStream.close();
        outputChannel.close();
        inputChannel.close();
    }
}
