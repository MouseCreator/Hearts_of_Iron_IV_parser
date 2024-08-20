package mouse.hoi.tools.parser.impl.writer;

import mouse.hoi.tools.parser.impl.writer.dw.DWData;
import org.springframework.stereotype.Service;

@Service
public class DWWriter {
    public String write(DWData dwData) {
        SpecialWriter specialWriter = new SpecialWriter();
        dwData.onRoot(specialWriter);
        return specialWriter.get();
    }
}
