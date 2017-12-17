package exampleone;

import java.util.function.Function;

public class ExampleRunner3 {

    // Using a tuples instead of currying
    private Function<Tuple3<Integer, Integer, Integer>, Integer> ft = this::calculate;

    private int calculate(Tuple3<Integer, Integer, Integer> x) {
        try {
            Thread.sleep(4000); // To show that cache is not being used
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return x._1 * x._2 * x._3;
    }

    private Function<Tuple3<Integer, Integer, Integer>, Integer> ftm = Memoizer.memoize(ft);

    private void automaticMemoizationExample3() {
        Tuple3<Integer, Integer, Integer> input = new Tuple3<>(2, 3, 4);
        Tuple3<Integer, Integer, Integer> input1 = new Tuple3<>(2, 3, 7);

        long startTime = System.currentTimeMillis();
        Integer result1 = ftm.apply(input);
        long time1 = System.currentTimeMillis() - startTime;

        startTime = System.currentTimeMillis();
        Integer result2 = ftm.apply(input);
        long time2 = System.currentTimeMillis() - startTime;

        startTime = System.currentTimeMillis();
        Integer result3 = ftm.apply(input1);
        long time3 = System.currentTimeMillis() - startTime;

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(time1);
        System.out.println(time2);
        System.out.println(time3);
    }

    public static void main(String[] args) {
        ExampleRunner3 exampleRunner = new ExampleRunner3();
        exampleRunner.automaticMemoizationExample3();
    }

}
