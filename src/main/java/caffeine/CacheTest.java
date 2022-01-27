package caffeine;

import com.github.benmanes.caffeine.cache.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author zhuang.ma
 * @date 2021/12/24
 */
public class CacheTest {

    public static void main(String[] args) {
        Object hello = syncOperator("hello");
        System.out.println(hello);
    }


    /**
     * 手动加载
     * 每次get 时候不存在指定
     *
     * @param key
     * @return
     */
    public static Object manulOperator(String key) {
        Cache<Object, Object> cache = Caffeine.newBuilder()
                .expireAfterAccess(1, TimeUnit.SECONDS)
                .expireAfterWrite(1, TimeUnit.SECONDS)
                .maximumSize(10)
                .build();

        //如果key 不存在则，进入指定函数
        Object value = cache.get(key, o -> o + "value");
        cache.put(key, value);
        //cache.invalidate(key);
        return cache.getIfPresent(key);
    }

    /**
     * 同步设置 加载
     *
     * @param key
     * @return
     */
    public static Object syncOperator(String key) {
        LoadingCache<String, Object> cache = Caffeine.newBuilder().expireAfterWrite(1, TimeUnit.SECONDS)
                .maximumSize(10).build(new CacheLoader<String, Object>() {
                    @Override
                    public Object load(String key) throws Exception {
                        return key + "value";
                    }
                });
        return cache.get(key);

    }

    /**
     * 异步加载
     *
     * @param key
     * @return
     */
    public Object asyncOperator(String key) {
        AsyncLoadingCache<Object, Object> cache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .buildAsync(k -> setAsyncValue(key).get());
        return cache.get(key);
    }

    private CompletableFuture<Object> setAsyncValue(String key) {
        return CompletableFuture.supplyAsync(() -> {
            return key + "value";
        });
    }


}
