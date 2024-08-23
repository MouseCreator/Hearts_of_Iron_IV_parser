package mouse.hoi.tools.parser.impl.split;

import lombok.Data;
import mouse.hoi.tools.parser.impl.dom.DomSimple;
import mouse.hoi.tools.parser.impl.reader.lr.StringValue;

import java.util.ArrayList;
import java.util.List;
@Data
public class SplitKeys {
    private final List<DomSimple> list;
    private SplitKeys(List<DomSimple> list) {
        this.list = list;
    }

    public static SplitKeys strings(List<String> stringKeys) {
        List<DomSimple> preprocess = new ArrayList<>();
        for (String str : stringKeys) {
            DomSimple simple = new DomSimple(new StringValue(str));
            preprocess.add(simple);
        }
        return new SplitKeys(preprocess);
    }
}
