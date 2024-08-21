package mouse.hoi.tools.dist;

import lombok.RequiredArgsConstructor;
import mouse.hoi.tools.random.RandomService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DistributionService {

    private final RandomService randomService;
    public int weightedDistribution(List<Double> list) {
        List<Double> cumulativeWeights = new ArrayList<>();
        double cumulativeSum = 0;

        for (double weight : list) {
            cumulativeSum += weight;
            cumulativeWeights.add(cumulativeSum);
        }
        double randomValue = randomService.rand().nextDouble() * cumulativeSum;
        for (int i = 0; i < cumulativeWeights.size(); i++) {
            if (randomValue <= cumulativeWeights.get(i)) {
                return i;
            }
        }

        return list.size() - 1;
    }
}
