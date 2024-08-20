package mouse.hoi.tools.parser.impl.writer.support;

import lombok.RequiredArgsConstructor;
import mouse.hoi.tools.parser.impl.writer.DataWriterManager;
import mouse.hoi.tools.parser.impl.writer.DataWriterManagerAware;
import mouse.hoi.tools.parser.impl.writer.style.ListStyle;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WriterSupport implements DataWriterManagerAware {
    private DataWriterManager writerManager;
    public DWSimples simple() {
        return new DWSimples();
    }
    public DWObjectBuilder build() {
        return new DWObjectBuilder(writerManager);
    }
    public DWObjectBuilder build(ObjectStyle style) {
        return new DWObjectBuilder(style, writerManager);
    }
    public DWListBuilder list(ListStyle style) {
        return new DWListBuilder(style);
    }

    @Override
    public void setDataWriterManager(DataWriterManager dataWriterManager) {
        this.writerManager = dataWriterManager;
    }
}
