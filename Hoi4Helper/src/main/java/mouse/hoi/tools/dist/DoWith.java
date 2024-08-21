package mouse.hoi.tools.dist;

import lombok.RequiredArgsConstructor;
import mouse.hoi.tools.random.RandomService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoWith {
    private final RandomService randomService;
    public void chance(double chance, Runnable r) {
        double v = randomService.rand().nextDouble();
        if (v < chance) {
            r.run();
        }
    }

    public boolean chance(double c) {
        double v = randomService.rand().nextDouble();
        return v < c;
    }
}
