package mouse.hoi.main.character.data;

import lombok.Data;
import mouse.hoi.main.common.data.scope.CountryScope;
import mouse.hoi.main.common.data.trigger.scoped.Triggers;

import java.util.List;

@Data
public class AiWillDoModifier {
    private List<AiWillDoOperator> operators;
    private Triggers conditions;
    public AiWillDoModifier() {
        conditions = new Triggers(new CountryScope());
    }
}
