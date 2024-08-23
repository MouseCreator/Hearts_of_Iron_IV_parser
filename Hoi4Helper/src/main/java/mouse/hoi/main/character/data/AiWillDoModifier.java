package mouse.hoi.main.character.data;

import lombok.Data;
import mouse.hoi.main.common.data.scope.CountryScope;
import mouse.hoi.main.common.data.trigger.Triggers;
@Data
public class AiWillDoModifier {
    private double factor;
    private Triggers conditions;

    public AiWillDoModifier() {
        conditions = new Triggers(new CountryScope());
    }
}
