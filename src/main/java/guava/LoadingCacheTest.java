package guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 缓存
 * Created by hujianbo on 2018/io1/25.
 */
public class LoadingCacheTest {

    public static void main(String[] args) {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .expireAfterAccess(1, TimeUnit.SECONDS)
                .maximumSize(1000)
                .build(new CacheLoader<String, String>() {
                           @Override
                           /** 当本地缓存命没有中时，调用load方法获取结果并将结果缓存 **/
                           public String load(String key) {
                               return key;
                           }

                       }
                );
        cache.put("k1", "v1");
        cache.put("k2", "v2");
        cache.put("k3", "v3");
        try {
            System.out.println(cache.get("k1"));
            TimeUnit.SECONDS.sleep(2);
            //输出k2
            System.out.println(cache.get("k2"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}
