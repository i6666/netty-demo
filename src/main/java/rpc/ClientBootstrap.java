package rpc;

/**
 * @author zhuang.ma
 * @date 2021/12/29
 */
public class ClientBootstrap {
    public static final String providerName = "UserService#sayHello#";

    public static void main(String[] args) throws InterruptedException {
        RpcConsumer rpcConsumer = new RpcConsumer();
        UserService service = (UserService)rpcConsumer.createProxy(UserService.class, providerName);
        for (;;){
            Thread.sleep(1000);
            System.out.println(service.sayHello("are you ok ?"));
        }
    }
}
