package mouse.hoi.tools.utils;


import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class TestIf {
    public static <T> TestResult<T> obj(T object, Predicate<T> predicate) {
        return new TestResult<>(object, predicate.test(object));
    }

    public static <T> TestResult<T> supply(Supplier<T> supplier, Predicate<T> predicate) {
        T t = supplier.get();
        return new TestResult<>(t, predicate.test(t));
    }
    public static TestResult<Boolean> ifTrue(Supplier<Boolean> supplier) {
        Boolean t = supplier.get();
        return new TestResult<>(t, t);
    }
    public static TestResult<Boolean> ifNot(Supplier<Boolean> supplier) {
        Boolean t = supplier.get();
        return new TestResult<>(t, !t);
    }

    public static class TestResult<T> {
        private final T object;
        private final boolean testResult;
        public TestResult(T object, boolean test) {
            this.testResult = test;
            this.object = object;
        }
        public void then(Consumer<T> consumer) {
            if (testResult) {
                consumer.accept(object);
            }
        }
    }
}
