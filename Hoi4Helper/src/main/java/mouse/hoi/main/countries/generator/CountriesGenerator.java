package mouse.hoi.main.countries.generator;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.countries.generator.data.*;
import mouse.hoi.tools.files.FileManager;
import mouse.hoi.tools.parser.impl.reader.ReaderService;
import mouse.hoi.tools.parser.impl.writer.WriterService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountriesGenerator {

    private final ReaderService readerService;
    private final WriterService writerService;
    private final FileManager fileManager;
    public void generateCountries(String baseDirectory, List<CountryInput> input) {
        createCountryTags(baseDirectory, input);
        createCountryFiles(baseDirectory, input);
        createCountryColors(baseDirectory, input);
        createHistory(baseDirectory, input);
    }

    private void createHistory(String baseDirectory, List<CountryInput> input) {
        String dir = baseDirectory + "/history/countries/";
        for (CountryInput c : input) {
            String filename = dir + c.getTag() + " - " + c.getFullName() + ".txt";
            String content =
            "capital = " + c.getCapital() + "\n" +
            "\n" +
            "starting_train_buffer = 1.0\n" +
            "\n" +
            "set_research_slots = 3\n" +
            "set_stability = 0.65\n" +
            "set_war_support = 0.25\n" +
            "set_convoys = 0\n" +
            "\n" +
            "set_technology = {\n" +
            "\tinfantry_weapons = 1\n" +
            "\tinfantry_weapons1 = 1\n" +
            "}\n" +
            "\n" +
            "set_politics = {\n" +
            "\truling_party = neutrality\n" +
            "\telections_allowed = no\n" +
            "}\n" +
            "\n" +
            "set_popularities = {\n" +
            "\tdemocratic = 0\n" +
            "\tfascism = 0\n" +
            "\tneutrality = 100\n" +
            "\tcommunism = 0\n" +
            "}\n\n" + c.getExtraContent() + "\n";
            fileManager.write(filename, content);
        }
    }

    private void createCountryColors(String baseDirectory, List<CountryInput> input) {
        String filename = baseDirectory + "/common/countries/colors.txt";
        CountryColorsWrapper wrapper = readerService.readFromFile(filename, CountryColorsWrapper.class);

        for (CountryInput c : input) {
            String tag = c.getTag();
            CountryColors colors = new CountryColors();
            colors.setTag(tag);
            ExtendedColor clr = new ExtendedColor("rgb", c.getR(), c.getG(), c.getB());
            colors.setColor(clr);
            colors.setUiColor(clr);
            wrapper.getColorsList().add(colors);
        }

        writerService.writeToFile(filename, wrapper);
    }

    private void createCountryTags(String baseDirectory, List<CountryInput> input) {
        String filename = baseDirectory + "/common/country_tags/00_countries.txt";
        CountryTags tags = readerService.readFromFile(filename, CountryTags.class);
        Set<String> set = tags.getCountryTagList().stream().map(CountryTag::getTag).collect(Collectors.toSet());
        for (CountryInput countryInput : input) {
            String tag = countryInput.getTag();
            if (set.contains(tag)) {
                continue;
            }
            String name = countryInput.getFullName();
            String asFile = fileFromName(name);
            CountryTag countryTag = new CountryTag(tag, asFile);

            tags.getCountryTagList().add(countryTag);
        }
        writerService.writeToFile(filename, tags);
    }

    private static String fileFromName(String name) {
        return "countries/" + name + ".txt";
    }

    private void createCountryFiles(String baseDirectory, List<CountryInput> input) {
        String fromDir = baseDirectory + "/common/";
        for (CountryInput c : input) {
            String filename = fromDir + fileFromName(c.getFullName());

            String content = "\n" +
                    "\n" +
                    "graphical_culture = western_european_gfx\n" +
                    "graphical_culture_2d = western_european_2d\n" +
                    "\n" +
                    String.format("color = { %d %d %d }", c.getR(), c.getG(), c.getB());
            fileManager.write(filename, content);
        }
    }
}
