package mouse.hoi.tools.parser.impl.reader.helper;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import mouse.hoi.tools.parser.impl.reader.lr.LRValues;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class Readers {
    private final LRValues lrValues;
    private final Interpreters interpreters;
    public LRValues lrValues() {
        return lrValues;
    }
    public Interpreters interpreters() {
        return interpreters;
    }

    private final List<ReaderAware> readerAwareList;

    @PostConstruct
    void init() {
        readerAwareList.forEach(r -> r.setReaders(this));
    }
}
