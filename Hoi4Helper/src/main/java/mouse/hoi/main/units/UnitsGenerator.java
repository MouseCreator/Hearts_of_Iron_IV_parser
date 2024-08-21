package mouse.hoi.main.units;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.states.data.VictoryPoint;
import mouse.hoi.tools.dist.DistributionService;
import mouse.hoi.tools.dist.DoWith;
import mouse.hoi.tools.files.FileManager;
import mouse.hoi.tools.parser.impl.writer.style.DoubleStyle;
import mouse.hoi.tools.random.RandomService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class UnitsGenerator {

    private final FileManager fileManager;
    private final RandomService randomService;
    private final DistributionService ds;
    private final DoWith doWith;
    public void generateUnits(CountryVictoryPoints victoryPoints, UnitsInput unitsInput, String baseDir) {
        String toCreateFiles = baseDir + "/history/units/";
        String suffix = unitsInput.getGlobalSuffix();
        for (CountryUnitsDescription input : unitsInput.getList()) {
            String tag = input.getTag();
            List<VictoryPoint> vps = victoryPoints.victoryPoints(tag);
            if (vps.isEmpty()) {
                throw new RuntimeException("Country " + tag + " has no victory points");
            }
            List<TemplateDescription> templates = input.getTemplates();
            String generated = generateUnitsText(vps, templates);
            String filename = toCreateFiles + tag + "_" + suffix + ".txt";
            fileManager.write(filename, generated);
        }
    }

    private String generateUnitsText(List<VictoryPoint> victoryPoints, List<TemplateDescription> templates) {
        List<Double> weights = new ArrayList<>();
        List<Integer> unitsHere = new ArrayList<>();
        for (int i = 0; i < victoryPoints.size(); i++) {
            VictoryPoint victoryPoint = victoryPoints.get(i);
            weights.add((double) victoryPoint.getPoints());
            unitsHere.add(0);
        }
        List<DivisionTemp> divisionTemps = new ArrayList<>();
        for (TemplateDescription template : templates) {
            for (int i = 0; i < template.getNumberDivisions(); i++) {
                DivisionTemp divisionTemp = new DivisionTemp();
                divisionTemp.order = i + 1;
                divisionTemp.template = template.getName();
                divisionTemp.training = generateTraining(template);
                divisionTemp.equipment = generateEquipment(template);

                int at = ds.weightedDistribution(weights);
                Integer atThisPos = unitsHere.get(at);
                int countUpdated = atThisPos + 1;
                unitsHere.set(at, countUpdated);

                Double weight = weights.get(at);
                double newWeight = Math.max(0, weight - 2);
                if (countUpdated > 6) {
                    newWeight = 0;
                }
                weights.set(at, newWeight);
                divisionTemp.location = victoryPoints.get(at).getProvince();
                divisionTemps.add(divisionTemp);
            }
        }
        return templatesToString(templates, divisionTemps);

    }

    private String templatesToString(List<TemplateDescription> templates, List<DivisionTemp> divisionTemps) {
        StringBuilder builder = new StringBuilder();

        for (TemplateDescription templateDescription : templates) {
            builder.append(templateDescription.getTemplateString());
        }

        builder.append("\nunits = {\n");
        DoubleStyle doubleStyle = DoubleStyle.defaultStyle();
        for (DivisionTemp d : divisionTemps) {
            builder.append("\tdivision = {\n");
            builder.append("\t\tname = \"").append(d.order).append(". ").append(d.template).append("\"\n");
            builder.append("\t\tlocation = ").append(d.location).append("\n");
            builder.append("\t\tdivision_template = \"").append(d.template).append("\"\n");
            builder.append("\t\tstart_experience_factor = ").append(doubleStyle.styled(d.training)).append("\n");
            builder.append("\t\tstart_equipment_factor = ").append(doubleStyle.styled(d.equipment)).append("\n");
            builder.append("\t}\n");
        }
        builder.append("}\n\n");

        return builder.toString();
    }

    private double generateTraining(TemplateDescription template) {
        double baseTraining = template.getTrainingLevel();
        double minusTraining = template.getTrainingLevelMinus();
        if (minusTraining > 0 && doWith.chance(0.45)) {
            baseTraining -= randomService.rand().nextDouble(0, minusTraining);
        }
        return baseTraining;
    }

    private double generateEquipment(TemplateDescription template) {
        double equipmentLevel = template.getEquipmentLevel();
        double equipmentMinus = template.getTrainingLevelMinus();
        if (equipmentMinus > 0 && doWith.chance(0.5)) {
            equipmentLevel -= randomService.rand().nextDouble(0, equipmentMinus);
        }
        return equipmentLevel;
    }

    private static class DivisionTemp {
        private int location;
        private String template;
        private int order;
        private double equipment;
        private double training;
    }
}
