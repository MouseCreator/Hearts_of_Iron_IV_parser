package mouse.hoi.main.character.data;

import lombok.Data;
import mouse.hoi.main.common.data.trigger.scoped.Triggers;

import java.util.ArrayList;
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
    private List<String> allowed;
    private List<String> available;
    private List<String> traits;
    private AiWillDo aiWillDo;

    public RoleAdvisor() {
        cost = 150;
        traits = new ArrayList<>();
    }
}
