package exampleone;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class ExampleRunner5 {

    // Using guava library

    private static LoadingCache<Integer, Integer> productCache;

    static {
        productCache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build(
                        new CacheLoader<Integer, Integer>() {
                            @Override
                            public Integer load(Integer number) throws Exception {
                                return calculateProduct(number);
                            }
                        }
                );
    }

    private static int calculateProduct(Integer number) {
        return 2 * number;
    }

    private Integer getProductUsingGuava(int id) throws ExecutionException {
        System.out.println("Cache Size:" + productCache.size());
        return productCache.get(id);
    }


    private void automaticMemoizationExample5() throws ExecutionException {
        long startTime = System.currentTimeMillis();
        Integer result1 = getProductUsingGuava(5);
        long time1 = System.currentTimeMillis() - startTime;

        startTime = System.currentTimeMillis();
        Integer result2 = getProductUsingGuava(5);
        long time2 = System.currentTimeMillis() - startTime;

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(time1);
        System.out.println(time2);
    }


    public static void main(String[] args) throws ExecutionException {
        ExampleRunner5 exampleRunner = new ExampleRunner5();
        exampleRunner.automaticMemoizationExample5();
    }
}
