package mouse.hoi.main.character.data;

import lombok.Data;

import java.util.List;

@Data
public class RoleNavyLeader implements CharacterRole {
    @Override
    public String roleName() {
        return "navy_leader";
    }

    private List<String> traits;
    private int skill;
    private int attackSkill;
    private int defenceSkill;
    private int maneuveringSkill;
    private int coordinationSkill;
    private int legacyId;
}
