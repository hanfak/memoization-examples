package exampleone;

import java.util.function.Function;

public class ExampleRunner2 {

// Using currying

    Function<Integer, Function<Integer, Integer>> f3 =
            x -> y -> x * y ;
    Function<Integer, Function<Integer, Integer>> f3m =
            Memoizer.memoize(x -> Memoizer.memoize(y -> x * y ));

    public void automaticMemoizationExample2() {
        long startTime = System.currentTimeMillis();
        Integer result1 = f3m.apply(2).apply(3);
        long time1 = System.currentTimeMillis() - startTime;
        startTime = System.currentTimeMillis();
        Integer result2 = f3m.apply(2).apply(3);
        long time2 = System.currentTimeMillis() - startTime;
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(time1);
        System.out.println(time2);
    }
    public static void main(String[] args) {
        ExampleRunner2 exampleRunner = new ExampleRunner2();
        exampleRunner.automaticMemoizationExample2();
    }

}
