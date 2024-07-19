package mouse.hoi.tools.properties;

import java.util.List;

public interface PropertyParser {
    PropertyMap parse(List<String> lines);
}
