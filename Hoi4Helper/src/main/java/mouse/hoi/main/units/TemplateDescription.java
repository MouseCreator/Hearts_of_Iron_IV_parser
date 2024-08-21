package mouse.hoi.main.units;

import lombok.Data;

@Data
public class TemplateDescription {
    private String templateString;
    private String name;
    private int numberDivisions;
    private double trainingLevel;
    private double trainingLevelMinus;
    private double equipmentLevel;
    private double equipmentLevelMinus;
}
