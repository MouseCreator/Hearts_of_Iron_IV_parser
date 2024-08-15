package mouse.hoi.main.common.data.effect.store;

import mouse.hoi.exception.EffectException;

import java.util.ArrayList;
import java.util.List;

public class EffectDataList implements EffectData{

    private final List<Object> objectList;

    public EffectDataList() {
        objectList = new ArrayList<>();
    }

    @Override
    public boolean isList() {
        return true;
    }

    @Override
    public EffectDataList list() {
        return this;
    }
    public void add(Object o) {
        objectList.add(o);
    }
    public List<Integer> integers() {
        List<Integer> result = new ArrayList<>();
        for (Object o : objectList) {
            if (o instanceof Integer i) {
                result.add(i);
            } else {
                throw new EffectException("Integer list contains " + o);
            }
        }
        return result;
    }

    public List<String> strings() {
        List<String> result = new ArrayList<>();
        for (Object o : objectList) {
            if (o instanceof String s) {
                result.add(s);
            } else {
                throw new EffectException("String list contains " + o);
            }
        }
        return result;
    }

    public List<?> objects() {
        return new ArrayList<>(objectList);
    }
}
