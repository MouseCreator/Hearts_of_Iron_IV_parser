package mouse.hoi.main.common.framework.effect;

import lombok.Data;
import mouse.hoi.main.common.data.scope.ScopeEnum;

import java.util.List;
@Data
public class EffectDescription {
    private final Class<?> effectClass;
    private final String key;
    private final List<ScopeEnum> scopeList;
}
