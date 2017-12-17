package exampleone;

import org.javatuples.Quartet;

import java.util.function.Function;
import java.util.stream.StreamSupport;

public class ExampleRunner4 {

    // Using a tuples from a library instead of currying
    // See library used here http://www.javatuples.org/using.html
    private Function<Quartet<Integer, Integer, Integer, Integer>, Integer> ft =
            x -> x.getValue0() * x.getValue1() * x.getValue2() * x.getValue3();
    private Function<Quartet<Integer, Integer, Integer, Integer>, Integer> ftm = Memoizer.memoize(ft);

    private void automaticMemoizationExample3() {
        Quartet<Integer, Integer, Integer, Integer> input = new Quartet<>(2, 3, 4, 5);

        // Playing around with streams
        StreamSupport.stream(input.spliterator(), false).map(x -> Integer.parseInt(x.toString()) * 3).forEach(System.out::println);
        System.out.println();

        long startTime = System.currentTimeMillis();
        Integer result1 = ftm.apply(input);
        long time1 = System.currentTimeMillis() - startTime;

        startTime = System.currentTimeMillis();
        Integer result2 = ftm.apply(input);
        long time2 = System.currentTimeMillis() - startTime;

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(time1);
        System.out.println(time2);
    }

    public static void main(String[] args) {
        ExampleRunner4 exampleRunner = new ExampleRunner4();
        exampleRunner.automaticMemoizationExample3();
    }

}
