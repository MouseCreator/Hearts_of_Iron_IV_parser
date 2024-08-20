package mouse.hoi.tools.database.repository;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.common.framework.effect.EffectHolderInitializer;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EffectsRepositoryImpl implements EffectsRepository {
    private final EffectHolderInitializer effectHolderInitializer;

    @Override
    public Set<String> allEffects() {
        return effectHolderInitializer.effectsHolder().allKeys();
    }

    @Override
    public boolean isEffect(String effect) {
       return effectHolderInitializer.effectsHolder().allKeys().contains(effect);
    }
}
