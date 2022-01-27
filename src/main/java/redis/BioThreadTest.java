package redis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zhuang.ma
 * @date 2021/12/21
 */
public class BioThreadTest {
    public static void main(String[] args) throws IOException {
       final ServerSocket server = new ServerSocket(8081);

        while (true) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Socket socket = null;
                    try {
                        socket = server.accept();
                        System.out.println("accept port:" + socket.getPort());
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                       String inData = null;
                        while ((inData =in.readLine()) != null){
                            System.out.println("client port:"+socket.getPort());
                            System.out.println("input data:"+inData);
                            System.out.println("current Thread name"+Thread.currentThread().getName());
                            if("close".equals(inData)) {
                                socket.close();
                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            }).start();


        }


    }


}
