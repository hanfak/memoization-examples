package exampleone;

import java.util.function.Function;

public class ExampleRunner {
    // Single input
    private Integer longCalculation(Integer x) {
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException ignored) {
        }
        return x * 2;
    }
    private Function<Integer, Integer> f = this::longCalculation;
    private Function<Integer, Integer> g = Memoizer.memoize(f);

    private void automaticMemoizationExample() {
        long startTime = System.currentTimeMillis();
        Integer result1 = g.apply(1);
        long time1 = System.currentTimeMillis() - startTime;
        startTime = System.currentTimeMillis();
        Integer result2 = g.apply(1); // memoization allows retrival of previous output from cache
        long time2 = System.currentTimeMillis() - startTime;
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(time1);
        System.out.println(time2); // Should 0 as no compute, as stored value
    }

    public static void main(String[] args) {
        ExampleRunner exampleRunner = new ExampleRunner();
        exampleRunner.automaticMemoizationExample();
    }
}
