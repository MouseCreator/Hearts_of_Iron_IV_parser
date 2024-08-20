package mouse.hoi.tools.parser.impl.categories;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.common.tester.TokenTester;
import mouse.hoi.tools.parser.impl.dom.DomKV;
import mouse.hoi.tools.parser.impl.dom.DomObject;
import mouse.hoi.tools.parser.impl.dom.DomSimple;
import mouse.hoi.tools.parser.impl.reader.lr.SimpleValue;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryAssigner {

    private final TokenTester tester;
    public CategoryMap mapCategory(DomObject object) {
        List<DomKV> domKVS = object.orderedKeyValues();
        CategoryMap categoryMap = new CategoryMap();
        for (DomKV domKV : domKVS) {
            TokenCategory category = categoryFor(domKV);
            categoryMap.addToCategory(category, domKV);
        }
        return categoryMap;
    }

    private TokenCategory categoryFor(DomKV domKV) {
        DomSimple key = domKV.key();
        SimpleValue val = key.val();
        if (val.isInteger()) {
            return TokenCategory.NUMBER;
        }
        if (val.isDate()) {
            return TokenCategory.DATE;
        }
        String stringKey = val.stringValue();
        if (stringKey.equals("if") || stringKey.equals("else_if") || stringKey.equals("else")) {
            return TokenCategory.FLOW;
        }
        if (stringKey.equals("and") || stringKey.equals("or") || stringKey.equals("not")) {
            return TokenCategory.OPERATOR;
        }
        if (isCountryTag(stringKey)) {
            return TokenCategory.TAG;
        }
        if (tester.isEffect(stringKey)) {
            return TokenCategory.EFFECT;
        }
        if (tester.isTrigger(stringKey)) {
            return TokenCategory.TRIGGER;
        }
        String lower = stringKey.toLowerCase();
        if (lower.startsWith("mio:")) {
            return TokenCategory.MIO;
        }
        if (lower.startsWith("var:")) {
            return TokenCategory.VARIABLE;
        }
        return TokenCategory.NONE;
    }

    private boolean isCountryTag(String stringKey) {
        return tester.isCountryTag(stringKey);
    }
}
