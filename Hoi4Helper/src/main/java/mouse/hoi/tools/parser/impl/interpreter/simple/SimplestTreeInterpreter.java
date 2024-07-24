package mouse.hoi.tools.parser.impl.interpreter.simple;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import mouse.hoi.exception.InterpretationException;
import mouse.hoi.main.gfx.data.Animation;
import mouse.hoi.main.gfx.data.SpriteType;
import mouse.hoi.main.gfx.data.SpriteTypes;
import mouse.hoi.main.gfx.data.SpriteTypesWrapper;
import mouse.hoi.tools.parser.annotation.WriteAs;
import mouse.hoi.tools.parser.data.DPos;
import mouse.hoi.tools.parser.impl.ast.*;
import mouse.hoi.tools.parser.impl.interpreter.TreeInterpreter;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

@Service
@Primary
public class SimplestTreeInterpreter implements TreeInterpreter {
    @Data
    private static class Scope {
        private final Class<?> clazz;
        private final String id;

        public Scope(Class<?> clazz, String id) {
            this.clazz = clazz;
            this.id = id;
        }
    }
    @Data
    private static class ScopeActions<T, Y> {
        private final Class<T> prevScope;
        private final Class<Y> nextScope;
        private final BiConsumer<T, Y> onStart;
        private final BiConsumer<T, Y> onEnd;

        public ScopeActions(Class<T> prevScope, Class<Y> nextScope, BiConsumer<T, Y> onStart, BiConsumer<T, Y> onEnd) {
            this.prevScope = prevScope;
            this.nextScope = nextScope;
            this.onStart = onStart;
            this.onEnd = onEnd;
        }
    }
    private final Map<Scope, ScopeActions<?,?>> map = new HashMap<>();
    @Override
    public <T> T mapToObject(AbstractSyntaxTree tree, Class<T> clazz) {
        T object = autoObject(clazz);
        RootNode root = tree.root();
        initializeWith(object, root);
        return object;
    }

    private void initializeAsField(String key, Object object, Node value) {

        Class<?> clazz = object.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        Object val = evaluateSimple(value);
        boolean initialized = false;
        for (Field field : declaredFields) {
            WriteAs annotation = field.getAnnotation(WriteAs.class);
            if (annotation == null)
                continue;
            String name = annotation.value();
            if (name.isEmpty()) {
                name = field.getName();
            }
            if (name.equalsIgnoreCase(key)) {
                initField(object, val, field);
                initialized = true;
                break;
            }
        }
        if (!initialized) {
            throw new InterpretationException("Cannot find a field to set " + key + " = " + val);
        }
    }

    private Object evaluateSimple(Node value) {
        if (value instanceof StringNode s) {
            return s.getValue();
        }
        if (value instanceof IdNode id) {
            return id.getId();
        }
        if (value instanceof DoubleNode d) {
            return d.getValue();
        }
        if (value instanceof IntegerNode i) {
            return i.getValue();
        }
        if (value instanceof BooleanNode b) {
            return b.isValue();
        }
        throw new InterpretationException("Unable to initialize field with " + value);
    }

    private <T, Y> void changeScope(ScopeActions<T, Y> actions, Object object, Node value) {
        Class<Y> nextScope = actions.getNextScope();
        Y newObject = autoObject(nextScope);
        T prevObject = actions.getPrevScope().cast(object);
        actions.getOnStart().accept(prevObject, newObject);
        initializeWith(newObject, value);
        actions.getOnEnd().accept(prevObject, newObject);
    }

    private void initializeWith(Object object, Node value) {
        if (value instanceof RootNode rootNode) {
            initializeWithList(object, rootNode.getChildren());
            return;
        }
        if (value instanceof BlockNode blockNode) {
            initializeWithList(object, blockNode.getChildren());
            return;
        }
        throw new InterpretationException("Cannot cast to object: " + value);
    }

    private void initializeWithList(Object object, List<Node> children) {
        Class<?> clazz = object.getClass();
        for (Node ch : children) {
            if (ch instanceof KeyValueNode kv) {
                Node key = kv.getKey();
                Node value = kv.getValue();
                if (key instanceof SimpleNode simpleNode) {
                    String fromSimpleNode = toKeyString(simpleNode);
                    ScopeActions<?, ?> actions = map.get(new Scope(clazz, fromSimpleNode));
                    if (actions == null) {
                        initializeAsField(fromSimpleNode, object, value);
                    } else {
                        changeScope(actions, object, value);
                    }
                } else {
                    throw new UnsupportedOperationException("Cannot get key: " + key);
                }
            } else {
                throw new InterpretationException("Unexpected node: " + ch);
            }
        }
    }
    private <T> T autoObject(Class<T> clazz) {
        T t = constructObject(clazz);
        if (t instanceof Inits i) {
            i.initialize();
        }
        return t;
    }
    private <T> T constructObject(Class<T> clazz) {
        try {
            Constructor<T> declaredConstructor = clazz.getDeclaredConstructor();
            return declaredConstructor.newInstance();
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new InterpretationException(e);
        }
    }

    private String toKeyString(SimpleNode simpleNode) {
        return simpleNode.print().toLowerCase();
    }
    @PostConstruct
    private void starts() {
        after(SpriteTypesWrapper.class).on("spriteTypes").begin(SpriteTypes.class)
                .whenEnds((t, y) -> t.getTypesList().add(y))
                .put();
        after(SpriteTypes.class).on("spriteType").begin(SpriteType.class)
                .whenEnds((t, y) -> t.getSpriteTypeList().addLast(y))
                .put();
        after(SpriteType.class).on("animation").begin(Animation.class)
                .whenEnds((t,y)->t.getAnimationList().add(y))
                .put();
        after(Animation.class).on("animationrotationoffset").begin(DPos.class)
                .whenEnds(Animation::setRotationOffset)
                .put();
        after(Animation.class).on("animationtexturescale").begin(DPos.class)
                .whenEnds(Animation::setTextureScale)
                .put();

    }

    private <T> AfterAction<T> after(Class<T> clazz) {
        return new AfterAction<>(clazz);
    }
    private class AfterAction<T> {
        private final Class<T> tClass;

        public AfterAction(Class<T> tClass) {
            this.tClass = tClass;
        }
        public OnAction<T> on(String expects) {
            String e = expects.toLowerCase();
            return new OnAction<>(tClass, e);
        }
    }
    private class OnAction<T> {
        private final Class<T> tClass;
        private final String expects;

        public OnAction(Class<T> tClass, String expects) {
            this.tClass = tClass;
            this.expects = expects;
        }

        public <Y> BeginAction<T, Y> begin(Class<Y> yClass) {
            return new BeginAction<>(tClass, yClass, expects);
        }
    }

    private class BeginAction<T, Y> {
        private final Class<T> tClass;
        private final Class<Y> yClass;
        private final String expects;

        public BeginAction(Class<T> tClass, Class<Y> yClass, String expects) {
            this.tClass = tClass;
            this.yClass = yClass;
            this.expects = expects;
        }

        private BiConsumer<T, Y> whenStarts = (a, b)->{};
        private BiConsumer<T, Y> whenEnds= (a,b)->{};
        public BeginAction<T, Y> whenStarts(BiConsumer<T, Y> biConsumer) {
            this.whenStarts = biConsumer;
            return this;
        }
        public BeginAction<T, Y> whenEnds(BiConsumer<T, Y> biConsumer) {
            this.whenEnds = biConsumer;
            return this;
        }
        public void put() {
            Scope scope = new Scope(tClass, expects);
            ScopeActions<T, Y> tyScopeActions = new ScopeActions<>(tClass, yClass, whenStarts, whenEnds);
            map.put(scope, tyScopeActions);
        }
    }

    private void initField(Object t, Object val, Field field) {
        try {
            field.setAccessible(true);
            field.set(t, val);
        } catch (IllegalAccessException e) {
            throw new InterpretationException(e);
        }
    }
}
