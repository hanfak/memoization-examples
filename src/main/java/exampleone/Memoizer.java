package exampleone;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class Memoizer {

    private static <T, U> Function<T, U> doMemoize(final Function<T, U> function) {
        Map<T, U> cache = new ConcurrentHashMap<>();
        return input -> cache.computeIfAbsent(input, function::apply);
    }
    static <T, U> Function<T, U> memoize(final Function<T, U> function) {
        return doMemoize(function);
    }

}