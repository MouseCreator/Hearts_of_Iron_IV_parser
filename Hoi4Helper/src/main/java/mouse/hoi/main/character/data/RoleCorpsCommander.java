package mouse.hoi.main.character.data;

import lombok.Data;

import java.util.List;

@Data
public class RoleCorpsCommander implements CharacterRole {
    private List<String> traits;
    private int skill;
    private int attackSkill;
    private int defenceSkill;
    private int planningSKill;
    private int logisticsSkill;
    private int legacyId;

    @Override
    public String roleName() {
        return "corps_commander";
    }
}
