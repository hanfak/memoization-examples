package exampleone;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.javatuples.Pair;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class ExampleRunner6 {

    // Using guava library and tuple library

    private static LoadingCache<Pair<Integer, Integer>, Integer> productCache;

    static {
        productCache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build(
                        new CacheLoader<Pair<Integer, Integer>, Integer>() {
                            @Override
                            public Integer load(Pair<Integer, Integer> numbers) throws Exception {
                                Thread.sleep(4000); // To show that cache is not being used
                                return numbers.getValue0() * numbers.getValue1();
                            }
                        }
                );
    }

    private Integer getProductUsingGuava(Pair<Integer, Integer> numbers) throws ExecutionException {
        System.out.println("Cache Size:" + productCache.size());
        return productCache.get(numbers);
    }


    private void automaticMemoizationExample6() throws ExecutionException {

        Pair<Integer, Integer> input = new Pair<>(2, 3);
        Pair<Integer, Integer> input1 = new Pair<>(4, 5);

        long startTime = System.currentTimeMillis();
        Integer result1 = getProductUsingGuava(input);
        long time1 = System.currentTimeMillis() - startTime;

        startTime = System.currentTimeMillis();
        Integer result2 = getProductUsingGuava(input);
        long time2 = System.currentTimeMillis() - startTime;

        startTime = System.currentTimeMillis();
        Integer result3 = getProductUsingGuava(input1);
        long time3 = System.currentTimeMillis() - startTime;

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(time1);
        System.out.println(time2);
        System.out.println(time3);
    }


    public static void main(String[] args) throws ExecutionException {
        ExampleRunner6 exampleRunner = new ExampleRunner6();
        exampleRunner.automaticMemoizationExample6();
    }
}
