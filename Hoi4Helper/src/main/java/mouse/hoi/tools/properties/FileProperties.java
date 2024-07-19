package mouse.hoi.tools.properties;

import lombok.AllArgsConstructor;
import mouse.hoi.tools.files.FileManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FileProperties {
    private final PropertyParser propertiesParser;
    private final FileManager fileManager;

    public PropertyMap readProperties(String filename) {
        List<String> lines = fileManager.readLines(filename);
        return propertiesParser.parse(lines);
    }
}
