package mouse.hoi.tools.parser.impl.reader.helper;

import lombok.AllArgsConstructor;
import mouse.hoi.tools.parser.impl.reader.lr.LRValues;
import org.springframework.stereotype.Service;

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

}
