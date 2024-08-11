package mouse.hoi.tools.properties;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PropertiesHelper {
    public List<String> listedProperties(String listed) {
        String[] split = listed.split(" ");
        List<String> result = new ArrayList<>();
        for(String s : split) {
            if (s.isEmpty())
                continue;
            result.add(s);
        }
        return result;
    }
}
