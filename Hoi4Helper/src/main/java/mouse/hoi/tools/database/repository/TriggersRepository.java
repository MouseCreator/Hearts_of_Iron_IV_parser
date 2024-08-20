package mouse.hoi.tools.database.repository;

import java.util.List;

public interface TriggersRepository {
    List<String> allTriggers();
    boolean isTrigger(String trigger);
}
