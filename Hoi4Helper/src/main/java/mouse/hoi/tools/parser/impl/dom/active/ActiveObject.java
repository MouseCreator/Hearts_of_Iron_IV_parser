package mouse.hoi.tools.parser.impl.dom.active;

import mouse.hoi.exception.DomException;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.DomSimple;
import mouse.hoi.tools.parser.impl.dom.query.SV;
import mouse.hoi.tools.parser.impl.reader.lr.SimpleValue;

import java.util.List;
import java.util.Optional;

public class ActiveObject {
    private final DomData domData;
    public ActiveObject(DomData domData) {
        this.domData = domData;
    }

    public boolean isObject() {
        return domData.isObject();
    }
    public boolean isSimple() {
        return domData.isSimple();
    }

    public String getString(String key) {
        DomSimple simple = getSimpleByKey(key);
        return simple.val().stringValue();
    }

    private DomSimple getSimpleByKey(String key) {
        List<DomData> dataList = getByKey(key);
        return onlyAndSimple(dataList);
    }

    private List<DomData> getByKey(String key) {
        return domData.object().get(new DomSimple(SV.string(key)));
    }

    private DomSimple onlyAndSimple(List<DomData> dataList) {
        DomData first = onlyOne(dataList);
        return first.simple();
    }

    private DomData onlyOne(List<DomData> dataList) {
        if (dataList.size() != 1) {
            throw new DomException("Expected to have one dom data, but got: " + dataList);
        }
        return dataList.getFirst();
    }

    private DomData onlyOneByKey(String key) {
        List<DomData> byKey = getByKey(key);
        return onlyOne(byKey);
    }

    private Optional<DomData> optionalByKey(String key) {
        List<DomData> byKey = getByKey(key);
        if (byKey.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(onlyOne(byKey));
    }

    public int getInteger(String key) {
        DomSimple simpleByKey = getSimpleByKey(key);
        return simpleByKey.val().intValue();
    }

    public boolean getBoolean(String key) {
        DomSimple simple = getSimpleByKey(key);
        return simple.val().boolValue();
    }

    public Optional<ActiveObject> optionalData(String key) {
        Optional<DomData> onlyByKey = optionalByKey(key);
        return onlyByKey.map(ActiveObject::new);
    }

    public SimpleValue simple() {
        return domData.simple().val();
    }

    public Optional<Boolean> optionalBoolean(String key) {
        Optional<DomSimple> domSimple = optionalAndSimple(key);
        return domSimple.map(d -> d.val().boolValue());
    }
    public Optional<String> optionalString(String key) {
        Optional<DomSimple> domSimple = optionalAndSimple(key);
        return domSimple.map(d -> d.val().stringValue());
    }

    private Optional<DomSimple> optionalAndSimple(String key) {
        Optional<DomData> d = optionalByKey(key);
        return d.map(DomData::simple);
    }
}
