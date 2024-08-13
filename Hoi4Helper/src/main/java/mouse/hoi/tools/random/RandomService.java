package mouse.hoi.tools.random;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomService {
    private final Random random = new Random();

    public Random rand() {
        return random;
    }
}
