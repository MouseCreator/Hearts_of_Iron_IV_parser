package mouse.hoi.main.common.data.effect.effects;

import mouse.hoi.main.common.data.effect.IntEffect;
import mouse.hoi.main.common.data.scope.ScopeEnum;

import java.util.List;
public class RandomizeWeatherEffect extends IntEffect {
    @Override
    public List<ScopeEnum> scopes() {
        return List.of(ScopeEnum.GLOBAL);
    }

    @Override
    public String getEffectName() {
        return "randomize_weather";
    }
}
