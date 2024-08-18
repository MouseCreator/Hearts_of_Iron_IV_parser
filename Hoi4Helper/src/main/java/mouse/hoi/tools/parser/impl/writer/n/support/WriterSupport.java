package mouse.hoi.tools.parser.impl.writer.n.support;

import lombok.RequiredArgsConstructor;
import mouse.hoi.tools.parser.impl.writer.n.DataWriterManager;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WriterSupport {
    private final DataWriterManager writerManager;
    public DWSimples simple() {
        return new DWSimples();
    }
    public DWObjectBuilder build() {
        return new DWObjectBuilder(writerManager);
    }
    public DWObjectBuilder build(ObjectStyle style) {
        return new DWObjectBuilder(style, writerManager);
    }
}
