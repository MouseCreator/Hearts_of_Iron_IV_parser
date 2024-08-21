package mouse.hoi.main.units;

import lombok.RequiredArgsConstructor;
import mouse.hoi.tools.files.FileManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnitInputLoader {
    private final FileManager fileManager;

    public UnitsInput load() {
        String filename = "src/main/resources/units/generate.input";
        List<String> lines = fileManager.readLines(filename);
        UnitsInput input = new UnitsInput();
        if (lines.isEmpty()) {
            return input;
        }
        String globalSuffix = lines.getFirst().trim();
        input.setGlobalSuffix(globalSuffix);
        CountryUnitsDescription cud = null;
        TemplateDescription templateDescription = null;
        StringBuilder builder = null;
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.startsWith(":") && line.endsWith(":")) {
                onTemplateEnd(builder, templateDescription);
                cud = new CountryUnitsDescription();
                String tag = line.replace(":", "").trim();
                cud.setTag(tag);
                input.getList().add(cud);
            } else if (line.startsWith(">>>")) {
                onTemplateEnd(builder, templateDescription);
                line = line.replace(">>>", "");
                builder = new StringBuilder();
                templateDescription = new TemplateDescription();
                if (cud == null) {
                    throw new RuntimeException("No country tag to set division template: " + cud);
                }
                cud.getTemplates().add(templateDescription);
                String[] split = line.split(";");
                trimAll(split);
                String templateName = split[0];
                int numberDivisions = 1;
                double trainingFactor = 0.3;
                double trainingFactorMinus = 0;
                double equipmentFactor = 1.0;
                double equipmentFactorMinus = 0;
                if (split.length > 1) {
                    numberDivisions = Integer.parseInt(split[1]);
                }
                if (split.length > 2) {
                    String active = split[2];
                    String[] minusSplit = active.split("-", 2);
                    trimAll(minusSplit);
                    trainingFactor = Double.parseDouble(minusSplit[0]);
                    if (minusSplit.length > 1) {
                        trainingFactorMinus = Double.parseDouble(minusSplit[1]);
                    }
                }
                if (split.length > 3) {
                    String active = split[3];
                    String[] minusSplit = active.split("-", 2);
                    trimAll(minusSplit);
                    equipmentFactor = Double.parseDouble(minusSplit[0]);
                    if (minusSplit.length > 1) {
                        equipmentFactorMinus = Double.parseDouble(minusSplit[1]);
                    }
                }
                templateDescription.setName(templateName);
                templateDescription.setNumberDivisions(numberDivisions);
                templateDescription.setTrainingLevel(trainingFactor);
                templateDescription.setTrainingLevelMinus(trainingFactorMinus);
                templateDescription.setEquipmentLevelMinus(equipmentFactorMinus);
                templateDescription.setEquipmentLevel(equipmentFactor);
            } else {
                if (builder != null) {
                    builder.append(line).append("\n");
                }
            }
        }
        onTemplateEnd(builder, templateDescription);
        return input;
    }

    private void onTemplateEnd(StringBuilder builder, TemplateDescription templateDescription) {
        if (templateDescription != null) {
            if (builder == null) {
                templateDescription.setTemplateString("");
            } else {
                templateDescription.setTemplateString(builder.toString());
            }
        }
    }

    private static void trimAll(String[] split) {
        for (int j = 0; j < split.length; j++) {
           split[j] = split[j].trim();
        }
    }
}
