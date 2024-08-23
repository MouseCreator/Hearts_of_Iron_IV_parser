package mouse.hoi.main.character.data;

import lombok.Data;

import java.util.List;

@Data
public class RoleFieldMarshal implements CharacterRole {
    private List<String> traits;
    private int skill;
    private int attackSkill;
    private int defenceSkill;
    private int planningSKill;
    private int logisticsSkill;
    private int legacyId;

    @Override
    public String roleName() {
        return "field_marshal";
    }
}
