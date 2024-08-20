package mouse.hoi.tools.database.repository;

import java.util.List;

public interface EffectsRepository {
    List<String> allEffects();
    void addEffect(String trigger);
    void removeEffect(String trigger);
    boolean isEffect(String trigger);
}
