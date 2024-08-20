package mouse.hoi.main.bookmark.service;

import lombok.RequiredArgsConstructor;
import mouse.hoi.tools.files.FileManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuildPropertiesReader {

    private final FileManager fileManager;
    public BuildingInputs read() {
        String filename = "src/main/resources/states/build.input";
        List<String> strings = fileManager.readLines(filename);
        BuildingInputs inputs = new BuildingInputs();
        BuildingDef def = new BuildingDef();
        for (String line : strings) {
            if (line.trim().isEmpty()) {
                continue;
            }
            if (line.contains(":")) {
                String[] split = line.split(":", 2);
                String activeTag = split[0].trim();
                def = new BuildingDef();
                inputs.put(activeTag, def);
            } else {
                String[] split = line.split("=", 2);
                String type = split[0].trim();
                int level = Integer.parseInt(split[1].trim());
                switch (type) {
                    case "civs" -> def.setCivs(level);
                    case "mils" -> def.setMils(level);
                    case "docs" -> def.setDocs(level);
                    default -> throw new RuntimeException("Unknown building: " + type);
                }
            }
        }
        return inputs;
    }
}
