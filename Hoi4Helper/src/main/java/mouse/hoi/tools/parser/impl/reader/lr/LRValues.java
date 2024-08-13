package mouse.hoi.tools.parser.impl.reader.lr;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import mouse.hoi.exception.ReaderException;
import mouse.hoi.tools.parser.impl.ast.BlockNode;
import mouse.hoi.tools.parser.impl.ast.Node;
import mouse.hoi.tools.parser.impl.ast.SimpleNode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.*;

@Service
public class LRValues {
    public LeftValueDynamic with(LeftValue lv, RightValue rv) {
        return new LeftValueDynamic(lv, rv);
    }

    public static class LeftValueDynamic {
        private final LeftValue leftValue;
        private final RightValue rightValue;
        private boolean consumed = false;
        public LeftValueDynamic(LeftValue lv, RightValue rv) {
            this.leftValue = lv;
            this.rightValue = rv;
        }
        public RightValueDynamic test(Predicate<String> stringPredicate) {
            if (consumed) {
                return rightMock();
            }
            String str = rightValue.stringValue();
            boolean validString = stringPredicate.test(str);
            if (!validString) {
                return rightMock();
            }
            consumed = true;
            return rightMock();
        }

        public RightValueDynamic onToken(String token) {
            if (consumed) {
                return rightMock();
            }
            String s = leftValue.stringValue();
            if (!s.equalsIgnoreCase(token)) {
                return rightMock();
            }
            consumed = true;
            return new RightValueDynamicImpl(this, rightValue);
        }

        private RightValueDynamicMock rightMock() {
            return new RightValueDynamicMock(this);
        }

        public void orElseThrow() {
            if (!consumed) {
                throw new ReaderException("Cannot consume left value: " + leftValue);
            }
        }

        public <R> LeftValueMapper<R> onInteger(Function<Integer, R> function) {
            if (leftValue.isInteger() && !consumed) {
                consumed = true;
                R applied = function.apply(leftValue.intValue());
                return new LeftValueMapperImpl<>(this, applied);
            }
            return new LeftValueMockMapper<>(this);
        }

        public RightValueDynamic onInteger() {
            if (leftValue.isInteger() && !consumed) {
                consumed = true;
                return new RightValueDynamicImpl(this, rightValue);
            }
            return rightMock();
        }
    }

    public interface RightValueDynamic {
        RightValueDynamic onBlock(Consumer<BlockNode> blockNode);
        <R> MappedResult<R> mapBlock(Function<BlockNode, R> blockNode);
        LeftValueDynamic or();
        void orElseThrow();
        LeftValueDynamic setString(Consumer<String> consumer);
        LeftValueDynamic setDouble(Consumer<Double> consumer);
        LeftValueDynamic setBoolean(Consumer<Boolean> consumer);
        LeftValueDynamic setInteger(Consumer<Integer> consumer);
        MappedResult<List<Integer>> integerList();
        MappedResult<List<String>> stringList();
    }

    public interface MappedResult<R> {
        LeftValueDynamic consume(Consumer<R> consumer);
        Optional<R> get();
        LeftValueDynamic push(Supplier<Collection<R>> collection);
    }

    @AllArgsConstructor
    public static class MappedResultImpl<R> implements MappedResult<R> {

        private final RightValueDynamic parent;

        private final R result;

        @Override
        public LeftValueDynamic consume(Consumer<R> consumer) {
            consumer.accept(result);
            return parent.or();
        }

        @Override
        public Optional<R> get() {
            return Optional.empty();
        }

        @Override
        public LeftValueDynamic push(Supplier<Collection<R>> collection) {
            collection.get().add(result);
            return parent.or();
        }
    }
    @AllArgsConstructor
    public static class MappedResultMock<R> implements MappedResult<R> {

        private final RightValueDynamic parent;

        @Override
        public LeftValueDynamic consume(Consumer<R> consumer) {
            return parent.or();
        }

        @Override
        public Optional<R> get() {
            return Optional.empty();
        }

        @Override
        public LeftValueDynamic push(Supplier<Collection<R>> collection) {
            return parent.or();
        }
    }
    @RequiredArgsConstructor
    public static class RightValueDynamicImpl implements RightValueDynamic {

        final LeftValueDynamic parent;
        final RightValue rv;
        private boolean consumed = false;
        @Override
        public RightValueDynamic onBlock(Consumer<BlockNode> blockNode) {
            if (rv.isBlock() && !consumed) {
                consumed = true;
                blockNode.accept(rv.blockValue());
            }
            return this;
        }

        @Override
        public <R> MappedResult<R> mapBlock(Function<BlockNode, R> blockNode) {
            if (rv.isBlock() && !consumed) {
                consumed = true;
                R apply = blockNode.apply(rv.blockValue());
                return new MappedResultImpl<>(this, apply);
            }
            return new MappedResultMock<>(this);
        }

        @Override
        public LeftValueDynamic or() {
            return parent;
        }

        @Override
        public void orElseThrow() {
            or().orElseThrow();
        }

        @Override
        public LeftValueDynamic setString(Consumer<String> consumer) {
            if (!consumed) {
                String s = rv.stringValue();
                consumer.accept(s);
            }
            return parent;
        }

        @Override
        public LeftValueDynamic setDouble(Consumer<Double> consumer) {
            if (!consumed && rv.isDouble()) {
                double v = rv.doubleValue();
                consumer.accept(v);
            }
            return parent;
        }

        @Override
        public LeftValueDynamic setBoolean(Consumer<Boolean> consumer) {
            if (!consumed && rv.isBoolean()) {
                boolean v = rv.boolValue();
                consumer.accept(v);
            }
            return parent;
        }

        @Override
        public LeftValueDynamic setInteger(Consumer<Integer> consumer) {
            if (!consumed && rv.isInteger()) {
                int v = rv.intValue();
                consumer.accept(v);
            }
            return parent;
        }

        @Override
        public MappedResult<List<Integer>> integerList() {
            if (consumed || !rv.isBlock()) {
                return new MappedResultMock<>(this);
            }
            List<Integer> result = new ArrayList<>();
            BlockNode blockNode = rv.blockValue();
            List<Node> children = blockNode.getChildren();
            for (Node n : children) {
                if (n instanceof SimpleNode sn) {
                    String print = sn.print();
                    int i = Integer.parseInt(print);
                    result.add(i);
                } else {
                    throw new ReaderException("Expected integer, but got: " + n);
                }
            }
            return new MappedResultImpl<>(this, result);
        }

        @Override
        public MappedResult<List<String>> stringList() {
            if (consumed || !rv.isBlock()) {
                return new MappedResultMock<>(this);
            }
            List<String> result = new ArrayList<>();
            BlockNode blockNode = rv.blockValue();
            List<Node> children = blockNode.getChildren();
            for (Node n : children) {
                if (n instanceof SimpleNode sn) {
                    String print = sn.print();
                    result.add(print);
                } else {
                    throw new ReaderException("Expected integer, but got: " + n);
                }
            }
            return new MappedResultImpl<>(this, result);
        }
    }
    @AllArgsConstructor
    public static class RightValueDynamicMock implements RightValueDynamic {

        final LeftValueDynamic parent;

        @Override
        public RightValueDynamic onBlock(Consumer<BlockNode> blockNode) {
            return this;
        }

        @Override
        public <R> MappedResult<R> mapBlock(Function<BlockNode, R> blockNode) {
            return new MappedResultMock<>(this);
        }

        @Override
        public LeftValueDynamic or() {
            return parent;
        }

        @Override
        public void orElseThrow() {
            or().orElseThrow();
        }

        @Override
        public LeftValueDynamic setString(Consumer<String> consumer) {
            return parent;
        }

        @Override
        public LeftValueDynamic setDouble(Consumer<Double> consumer) {
            return parent;
        }

        @Override
        public LeftValueDynamic setBoolean(Consumer<Boolean> consumer) {
            return parent;
        }

        @Override
        public LeftValueDynamic setInteger(Consumer<Integer> consumer) {
            return parent;
        }

        @Override
        public MappedResult<List<Integer>> integerList() {
            return new MappedResultMock<>(this);
        }

        @Override
        public MappedResult<List<String>> stringList() {
            return new MappedResultMock<>(this);
        }
    }
    public interface LeftValueMapper<R> {
        LeftValueDynamic then(Consumer<R> consumer);
        LeftValueDynamic then(BiConsumer<R, RightValue> consumer, RightValue rightValue);
        Optional<R> get();
    }
    public static class LeftValueMockMapper<R> implements LeftValueMapper<R> {
        private final LeftValueDynamic parent;

        public LeftValueMockMapper(LeftValueDynamic leftValueDynamic) {
            this.parent = leftValueDynamic;
        }

        @Override
        public LeftValueDynamic then(Consumer<R> consumer) {
            return parent;
        }

        @Override
        public LeftValueDynamic then(BiConsumer<R, RightValue> consumer, RightValue rightValue) {
            return parent;
        }

        @Override
        public Optional<R> get() {
            return Optional.empty();
        }
    }

    public static class LeftValueMapperImpl<R> implements LeftValueMapper<R> {
        private final LeftValueDynamic parent;
        private final R val;

        public LeftValueMapperImpl(LeftValueDynamic leftValueDynamic, R val) {
            this.parent = leftValueDynamic;
            this.val = val;
        }

        @Override
        public LeftValueDynamic then(Consumer<R> consumer) {
            consumer.accept(val);
            return parent;
        }

        @Override
        public LeftValueDynamic then(BiConsumer<R, RightValue> consumer, RightValue rightValue) {
            consumer.accept(val, rightValue);
            return parent;
        }

        @Override
        public Optional<R> get() {
            return Optional.ofNullable(val);
        }
    }

}
