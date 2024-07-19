package mouse.hoi.tools.properties;

import mouse.hoi.exception.PropertyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertiesParserImpl implements PropertyParser {
    public PropertyMap parse(List<String> lines) {
        PropertyMap map = new PropertyMap();
        for (String line : lines) {
            processLine(line, map);
        }
        return map;
    }

    private void processLine(String line, PropertyMap map) {
        String trimmed = line.trim();
        if (trimmed.isEmpty()) {
            return;
        }
        if (trimmed.startsWith("#")) {
            return;
        }
        String[] split = line.split("=", 2);
        if (split.length != 2) {
            throw new PropertyException("Cannot split line: " + line + ". Maybe there is no '=' separator");
        }
        String name = split[0].trim();
        String value = split[1].trim();
        value = processValue(value);
        map.put(name, value);
    }

    private String processValue(String value) {
        if (value.startsWith("\"") && value.endsWith("\"")){
            return value.substring(1, value.length()-1);
        }
        return value;
    }
}
