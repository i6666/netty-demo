package redis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 单线程阻塞
 */
public class BioTest {
 
    public static void main(String[] args) throws IOException {
        ServerSocket server=new ServerSocket(8081);
        while(true) {
            Socket socket=server.accept();
            System.out.println("accept port:"+socket.getPort());
            BufferedReader  in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String inData=null;
            try {
                while ((inData = in.readLine()) != null) {
                    System.out.println("client port:"+socket.getPort());
                    System.out.println("input data:"+inData);
                    if("close".equals(inData)) {
                        socket.close();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }      
        }
    }
}