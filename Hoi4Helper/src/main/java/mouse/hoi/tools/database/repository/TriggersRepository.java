package mouse.hoi.tools.database.repository;

import java.util.List;

public interface TriggersRepository {
    List<String> allTriggers();
    void addTrigger(String trigger);
    void removeTrigger(String trigger);
    boolean isTrigger(String trigger);
}
