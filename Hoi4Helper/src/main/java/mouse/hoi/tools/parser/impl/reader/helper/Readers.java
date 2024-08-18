package mouse.hoi.tools.parser.impl.reader.helper;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import mouse.hoi.tools.parser.impl.dom.interpreter.InterpreterManager;
import mouse.hoi.tools.parser.impl.dom.query.DomQueryService;
import mouse.hoi.tools.parser.impl.reader.lr.LRValues;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class Readers {
    private final DomQueryService lrValues;
    private final InterpreterManager interpreters;
    public DomQueryService lrValues() {
        return lrValues;
    }
    public InterpreterManager interpreters() {
        return interpreters;
    }

    private final List<ReaderAware> readerAwareList;

    @PostConstruct
    void init() {
        readerAwareList.forEach(r -> r.setReaders(this));
    }
}
