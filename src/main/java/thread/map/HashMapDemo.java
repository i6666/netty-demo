package thread.map;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.locks.LockSupport;

/**
 * @author zhuang.ma
 * @date 2022/1/25
 */
public class HashMapDemo {
    public static void main(String[] args) {

        HashMap<String, String> map = new HashMap<>();
        ConcurrentHashMap<Object, Object> hashMap = new ConcurrentHashMap<>();
        map.put("12","555");

        DelayQueue<Delayed> delayeds = new DelayQueue<>();
        LockSupport.park();
    }



}
