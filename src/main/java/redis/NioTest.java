package redis;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
 

 
public class NioTest {
 
    public static void main(String[] args) throws IOException {
        final ServerSocket server=new ServerSocket(8082);
        server.setSoTimeout(1000);
        List<Socket> sockets=new ArrayList<Socket>();
        while (true) {
            Socket socket = null;
            try {
                socket = server.accept();
                socket.setSoTimeout(500);
                sockets.add(socket);
                System.out.println("accept client port:"+socket.getPort());
            } catch (SocketTimeoutException e) {
                System.out.println("accept timeout");
            }
            //模拟非阻塞：轮询已连接的socket，每个socket等待10MS，有数据就处理，无数据就返回，继续轮询
            if( sockets.size()> 0) {
                for(Socket socketTemp:sockets ) {
                    try {
                        BufferedReader  in=new BufferedReader(new InputStreamReader(socketTemp.getInputStream()));
                        String inData=null;
                        while ((inData = in.readLine()) != null) {
                            System.out.println("input data client port:"+socketTemp.getPort());
                            System.out.println("input data client port:"+socketTemp.getPort() +"data:"+inData);
                            if("close".equals(inData)) {
                                socketTemp.close();
                            }
                        }
                    } catch (SocketTimeoutException e) {
                        System.out.println("input client loop"+socketTemp.getPort());
                    }
                }
            }
        }
 
    }
}
