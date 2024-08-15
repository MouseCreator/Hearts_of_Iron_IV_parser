package mouse.hoi.main.common.data.effect.effects;

import mouse.hoi.main.common.data.effect.StringEffect;
import mouse.hoi.main.common.data.scope.ScopeEnum;

import java.util.List;
public class TransferStateToEffect extends StringEffect {
    @Override
    public List<ScopeEnum> scopes() {
        return List.of(ScopeEnum.STATE);
    }

    @Override
    public String getEffectName() {
        return "transfer_state_to";
    }
}
