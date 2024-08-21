package mouse.hoi.main.countries.generator;

import lombok.RequiredArgsConstructor;
import mouse.hoi.tools.files.FileManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryInputLoader {
    private final FileManager fileManager;
    public List<CountryInput> createInput() {
        String filename = "src/main/resources/countries/generate.input";
        List<String> lines = fileManager.readLines(filename);
        List<CountryInput> countryInputs = new ArrayList<>();
        CountryInput currentActive = null;
        StringBuilder builder = new StringBuilder();
        for (String line : lines) {
            if (line.contains(":")) {
                if (currentActive != null) {
                    currentActive.setExtraContent(builder.toString());
                }
                currentActive = new CountryInput();
                countryInputs.add(currentActive);
                builder = new StringBuilder();
                String[] split = line.split(":", 4);
                String tag = split[0].trim().toUpperCase();
                String fullName = split[1].trim();
                String colors = split[2].trim();
                String[] colorSplit = colors.split(" +", 3);
                int r = Integer.parseInt(colorSplit[0]);
                int g = Integer.parseInt(colorSplit[1]);
                int b = Integer.parseInt(colorSplit[2]);
                int capital = Integer.parseInt(split[3].trim());
                currentActive.setTag(tag);
                currentActive.setFullName(fullName);
                currentActive.setR(r);
                currentActive.setG(g);
                currentActive.setB(b);
                currentActive.setCapital(capital);
            } else {
                builder.append(line).append("\n");
            }
        }
        if (currentActive != null) {
            currentActive.setExtraContent(builder.toString());
        }
        return countryInputs;
    }
}
