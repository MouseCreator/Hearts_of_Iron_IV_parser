package mouse.hoi.main.states.service.alias;

import lombok.RequiredArgsConstructor;
import mouse.hoi.tools.context.AppService;
import mouse.hoi.tools.files.PathsLoader;
import mouse.hoi.tools.properties.FileProperties;
import mouse.hoi.tools.properties.PropertyMap;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AliasGenerator implements AppService {
    private final PathsLoader pathsLoader;
    private final FileProperties fileProperties;
    @Override
    public void start() {
        PropertyMap map = fileProperties.readProperties("src/main/resources/states/init.input");
        String statesDirectory = map.expectedProperty("directory");
        PropertyMap alias = fileProperties.readProperties("src/main/resources/states/alias.input");
        List<String> fileNames = new ArrayList<>(pathsLoader.allFiles(statesDirectory, false, false));
        fileNames.sort(specialComparator);
        Map<String, String> aliasMap = createAliasMap(alias);
        boolean canSkip = true;
        if (new HashSet<>(aliasMap.values()).contains("SKIP")) {
            canSkip = false;
        }
        StringBuilder result = new StringBuilder();
        for (String filename : fileNames) {
            StateNameInfo stateNameInfo = getInfoFromName(filename);
            int id =stateNameInfo.id();
            String tag = stateNameInfo.tag();
            String country = aliasMap.get(tag);
            if (country == null) {
                if (canSkip)
                    continue;
                else
                    throw new RuntimeException("Invalid tag: " + tag + " for state " + filename);
            }
            if (country.equals("SKIP")) {
                continue;
            }
            String toWrite = String.format("\t%d = {\n\t\tadd_core_of = %s\n\t\ttransfer_state_to = %s\n\t}\n", id, country, country);
            result.append(toWrite);
        }
        System.out.println(result);
    }
    private static final Comparator<String> specialComparator = (s1, s2) -> {
        int m = Math.min(s1.length(), s2.length());
        int n1 = 0;
        int n2 = 0;
        int mult = 1;
        for (int i = 0; i < m; i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if (Character.isDigit(c1) && Character.isDigit(c2)) {
                n1 += mult * (c1 - '0');
                n2 += mult * (c1 - '0');
                mult *= 10;
                continue;
            }
            if (!Character.isDigit(c1) && !Character.isDigit(c2)) {
                break;
            }
            if (Character.isDigit(c1)) {
                return 1;
            } else {
                return -1;
            }
        }
        return n1 - n2;
    };
    private StateNameInfo getInfoFromName(String filename) {
        String[] split = filename.split("-", 2);
        int id = Integer.parseInt(split[0]);
        String[] split2 = split[1].split("\\.", 2);
        StringBuilder tagBuilder = new StringBuilder();
        StringBuilder numStr = new StringBuilder();
        for (char ch : split2[0].toCharArray()) {
            if (Character.isAlphabetic(ch)) {
                tagBuilder.append(ch);
            }
            else if (Character.isDigit(ch)) {
                numStr.append(ch);
            }
        }
        String tag = tagBuilder.toString().toUpperCase();
        int number;
        if (numStr.isEmpty()) {
            number = 0;
        } else {
            number = Integer.parseInt(numStr.toString());
        }
        return new StateNameInfo(id, tag, number);
    }

    private record StateNameInfo(int id, String tag, int number) {
    }

    private Map<String,String> createAliasMap(PropertyMap alias) {
        Collection<String> keys = alias.keys();
        Map<String, String> result = new HashMap<>();
        for (String key : keys) {
            String all = alias.expectedProperty(key);
            String[] separate = all.split(" +");
            for (String sep : separate) {
                if (sep.isEmpty())
                    continue;
                result.put(sep.toUpperCase(), key.toUpperCase());
            }
        }
        return result;
    }
}
