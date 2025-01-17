package mouse.hoi.main.states.service.categories;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.states.service.commons.StatesProvinces;
import mouse.hoi.tools.context.AppService;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class StateCategoryService implements AppService {
    private final StateCategoryAssigner stateCategoryAssigner;
    private final StatesProvinces statesProvinces;
    @Override
    public void start() {
        statesProvinces.apply(stateCategoryAssigner::assignCategory);
    }
}
