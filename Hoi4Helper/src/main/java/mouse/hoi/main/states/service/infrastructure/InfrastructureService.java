package mouse.hoi.main.states.service.infrastructure;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.states.service.commons.StatesProvinces;
import mouse.hoi.tools.context.AppService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InfrastructureService implements AppService {
    private final StatesProvinces statesProvinces;
    private final InfrastructureGenerator infrastructureGenerator;
    @Override
    public void start() {
        statesProvinces.apply(infrastructureGenerator::assignInfrastructureLevel);
    }
}
