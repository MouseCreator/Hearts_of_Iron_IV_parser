package mouse.hoi.tools.parser.impl.reader.lr;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import mouse.hoi.exception.ReaderException;
import mouse.hoi.tools.parser.impl.ast.BlockNode;
import mouse.hoi.tools.parser.impl.ast.Node;
import mouse.hoi.tools.parser.impl.ast.SimpleNode;
import mouse.hoi.tools.parser.impl.reader.helper.ReaderAware;
import mouse.hoi.tools.parser.impl.reader.helper.Readers;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.*;

@Service
public class LRValues implements ReaderAware {
    public LeftValueDynamic with(LeftValue lv, RightValue rv) {
        return new LeftValueDynamic(lv, rv, this);
    }
    Readers readers;
    @Override
    public void setReaders(Readers readers) {
        this.readers = readers;
    }

    public static class LeftValueDynamic {
        private final LeftValue leftValue;
        private final RightValue rightValue;
        private final LRValues root;
        private boolean consumed = false;
        public LeftValueDynamic(LeftValue lv, RightValue rv, LRValues root) {
            this.leftValue = lv;
            this.rightValue = rv;
            this.root = root;
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
                return new RightValueDynamicImpl(this, rightValue);
            }
            return rightMock();
        }

        public RememberedValue<String> rememberString() {
            if (consumed) {
                return new RememberedValueMock<>(this);
            }
            String s = leftValue.stringValue();
            return new RememberedValueImpl<>(s, this, rightValue);
        }

        public RememberedValue<Integer> rememberInteger() {
            if (consumed) {
                return new RememberedValueMock<>(this);
            }
            if (leftValue.isInteger()) {
                int i = leftValue.intValue();
                return new RememberedValueImpl<>(i, this, rightValue);
            }
            return new RememberedValueMock<>(this);
        }

        public void orElse(Runnable r) {
            if (!consumed) {
                r.run();
            }
            consumed = true;
        }
    }
    public interface RememberedValue<T> {
        RememberedValue<T> test(Predicate<T> test);
        <R> RememberedValue<R> map(Function<T, R> function);
        MappedResult<T> res();
        RememberedValue<T> onBlock(BiConsumer<T, BlockNode> blockNodeConsumer);
        RememberedValue<T> onBlock();
        RememberedValue<T> onString(BiConsumer<T, String> strConsumer);
        RememberedValue<T> onInteger(BiConsumer<T, Integer> intConsumer);
        RememberedValue<T> onDouble(BiConsumer<T, Double> doubleNodeConsumer);
        RememberedValue<T> onBoolean(BiConsumer<T, Boolean> boolConsumer);
    }
    public static class RememberedValueImpl<T> implements RememberedValue<T> {
         private final T value;
         private final LeftValueDynamic parent;
         private final RightValue rightValue;
         private boolean consumed = false;
         public RememberedValueImpl(T v, LeftValueDynamic parent, RightValue rightValue) {
             this.value = v;
             this.parent = parent;
             this.rightValue = rightValue;
         }

         public RememberedValue<T> test(Predicate<T> test) {
             boolean isGood = test.test(value);
             if (isGood) {
                 return this;
             }
             return new RememberedValueMock<>(parent);
         }

        @Override
        public <R> RememberedValue<R> map(Function<T, R> function) {
            R apply = function.apply(value);
            return new RememberedValueImpl<>(apply, parent, rightValue);
        }

        @Override
        public MappedResult<T> res() {
             parent.consumed = true;
             return new MappedResultImpl<>(parent, value);
        }

        @Override
        public RememberedValue<T> onBlock(BiConsumer<T, BlockNode> blockNodeConsumer) {
             if (rightValue.isBlock() && !consumed) {
                 consumed = true;
                 blockNodeConsumer.accept(value, rightValue.blockValue());
             }
             return this;
        }

        @Override
        public RememberedValue<T> onBlock() {
             return onBlock(parent.root.readers.interpreters()::readObj);
        }

        @Override
        public RememberedValue<T> onString(BiConsumer<T, String> stringNodeConsumer) {
            if (!consumed) {
                consumed = true;
                stringNodeConsumer.accept(value, rightValue.stringValue());
            }
            return this;
        }

        @Override
        public RememberedValue<T> onInteger(BiConsumer<T, Integer> consumer) {
            if (!consumed && rightValue.isInteger()) {
                consumed = true;
                consumer.accept(value, rightValue.intValue());
            }
            return this;
        }

        @Override
        public RememberedValue<T> onDouble(BiConsumer<T, Double> consumer) {
            if (!consumed && rightValue.isDouble()) {
                consumed = true;
                consumer.accept(value, rightValue.doubleValue());
            }
            return this;
        }

        @Override
        public RememberedValue<T> onBoolean(BiConsumer<T, Boolean> boolConsumer) {
            if (!consumed && rightValue.isBoolean()) {
                consumed = true;
                boolConsumer.accept(value, rightValue.boolValue());
            }
            return this;
        }

    }

    public static class RememberedValueMock<T> implements RememberedValue<T> {
        private final LeftValueDynamic parent;
        public RememberedValueMock(LeftValueDynamic parent) {
            this.parent = parent;
        }

        public RememberedValue<T> test(Predicate<T> test) {
            return this;
        }

        @Override
        public <R> RememberedValue<R> map(Function<T, R> function) {
            return new RememberedValueMock<>(parent);
        }

        @Override
        public MappedResult<T> res() {
            return new MappedResultMock<>(parent);
        }

        @Override
        public RememberedValue<T> onBlock(BiConsumer<T, BlockNode> blockNodeConsumer) {
            return this;
        }

        @Override
        public RememberedValue<T> onBlock() {
            return new RememberedValueMock<>(parent);
        }

        @Override
        public RememberedValue<T> onString(BiConsumer<T, String> stringNodeConsumer) {
            return this;
        }

        @Override
        public RememberedValue<T> onInteger(BiConsumer<T, Integer> intConsumer) {
            return this;
        }

        @Override
        public RememberedValue<T> onDouble(BiConsumer<T, Double> doubleNodeConsumer) {
            return this;
        }

        @Override
        public RememberedValue<T> onBoolean(BiConsumer<T, Boolean> boolConsumer) {
            return this;
        }

    }


    public interface RightValueDynamic {
        RightValueDynamic onBlock(Consumer<BlockNode> blockNode);
        <R> MappedResult<R> mapBlock(Function<BlockNode, R> blockNode);
        <R> MappedResult<R> mapBlock(Class<R> mapTo);
        LeftValueDynamic or();
        void orElseThrow();
        LeftValueDynamic setString(Consumer<String> consumer);
        LeftValueDynamic setDouble(Consumer<Double> consumer);
        LeftValueDynamic setBoolean(Consumer<Boolean> consumer);
        LeftValueDynamic setInteger(Consumer<Integer> consumer);
        MappedResult<List<Integer>> integerList();
        MappedResult<List<String>> stringList();
        <T> RememberedValue<T> init(Supplier<T> object);
        LeftValueDynamic skip();
    }



    public interface MappedResult<R> {
        LeftValueDynamic consume(Consumer<R> consumer);
        Optional<R> get();
        LeftValueDynamic push(Supplier<Collection<R>> collection);
    }

    @AllArgsConstructor
    public static class MappedResultImpl<R> implements MappedResult<R> {

        private final LeftValueDynamic parent;

        private final R result;

        @Override
        public LeftValueDynamic consume(Consumer<R> consumer) {
            consumer.accept(result);
            return parent;
        }

        @Override
        public Optional<R> get() {
            return Optional.empty();
        }

        @Override
        public LeftValueDynamic push(Supplier<Collection<R>> collection) {
            collection.get().add(result);
            return parent;
        }
    }
    @AllArgsConstructor
    public static class MappedResultMock<R> implements MappedResult<R> {

        private final LeftValueDynamic parent;

        @Override
        public LeftValueDynamic consume(Consumer<R> consumer) {
            return parent;
        }

        @Override
        public Optional<R> get() {
            return Optional.empty();
        }

        @Override
        public LeftValueDynamic push(Supplier<Collection<R>> collection) {
            return parent;
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
                return new MappedResultImpl<>(parent, apply);
            }
            return new MappedResultMock<>(parent);
        }

        @Override
        public <R> MappedResult<R> mapBlock(Class<R> mapTo) {
            return mapBlock(r -> parent.root.readers.interpreters().read(mapTo, r));
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
                return new MappedResultMock<>(parent);
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
            return new MappedResultImpl<>(parent, result);
        }

        @Override
        public MappedResult<List<String>> stringList() {
            if (consumed || !rv.isBlock()) {
                return new MappedResultMock<>(parent);
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
            return new MappedResultImpl<>(parent, result);
        }

        @Override
        public <T> RememberedValue<T> init(Supplier<T> object) {
            T t = object.get();
            return new RememberedValueImpl<>(t, parent, rv);
        }

        @Override
        public LeftValueDynamic skip() {
            return parent;
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
            return new MappedResultMock<>(parent);
        }

        @Override
        public <R> MappedResult<R> mapBlock(Class<R> mapTo) {
            return new MappedResultMock<>(parent);
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
            return new MappedResultMock<>(parent);
        }

        @Override
        public MappedResult<List<String>> stringList() {
            return new MappedResultMock<>(parent);
        }

        @Override
        public <T> RememberedValue<T> init(Supplier<T> object) {
            return new RememberedValueMock<>(parent);
        }

        @Override
        public LeftValueDynamic skip() {
            return parent;
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
