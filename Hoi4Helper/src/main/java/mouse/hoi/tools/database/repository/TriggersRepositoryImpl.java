package mouse.hoi.tools.database.repository;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TriggersRepositoryImpl implements TriggersRepository {
    @Override
    public List<String> allTriggers() {
        return List.of();
    }

    @Override
    public boolean isTrigger(String trigger) {
        return false;
    }
}
