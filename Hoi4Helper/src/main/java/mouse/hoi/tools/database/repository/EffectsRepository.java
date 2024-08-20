package mouse.hoi.tools.database.repository;

import java.util.Set;

public interface EffectsRepository {
    Set<String> allEffects();
    boolean isEffect(String trigger);
}
