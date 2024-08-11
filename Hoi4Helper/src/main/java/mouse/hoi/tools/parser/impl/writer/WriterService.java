package mouse.hoi.tools.parser.impl.writer;

import lombok.RequiredArgsConstructor;
import mouse.hoi.tools.files.FileManager;
import mouse.hoi.tools.parser.impl.writer.engine.WriterEngine;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WriterService {
    private final WriterEngine engine;
    private final FileManager fileManager;
    public void writeToFile(String filename, Object object) {
        String objectAsString = engine.write(object);
        fileManager.write(filename, objectAsString);
    }

}
