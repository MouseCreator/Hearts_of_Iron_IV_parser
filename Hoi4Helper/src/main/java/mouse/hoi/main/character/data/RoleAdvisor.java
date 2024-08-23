package mouse.hoi.main.character.data;

import lombok.Data;
import mouse.hoi.main.common.data.trigger.Triggers;

import java.util.List;

@Data
public class RoleAdvisor implements CharacterRole{
    @Override
    public String roleName() {
        return "advisor";
    }

    @Override
    public boolean requiresSmallPortrait() {
        return true;
    }

    private int cost;
    private String slot;
    private String ledger;
    private String token;
    private Triggers allowed;
    private List<String> traits;
    private AiWillDo aiWillDo;
}
