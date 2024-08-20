package mouse.hoi.tools.parser.impl.split;

import mouse.hoi.tools.parser.impl.dom.DomKV;

import java.util.function.Predicate;

public class CategorySplitDescription implements Predicate<DomKV> {

    private final Predicate<DomKV> predicate;

    public CategorySplitDescription(Predicate<DomKV> predicate) {
        this.predicate = predicate;
    }

    public boolean test(DomKV domKV) {
        return predicate.test(domKV);
    }
}
