package rpc;

/**
 * @author zhuang.ma
 * @date 2021/12/29
 */
public class ServiceBootstrap {
    public static void main(String[] args) throws InterruptedException {
        UserServiceImpl.starServer("localhost",8081);
    }
}
