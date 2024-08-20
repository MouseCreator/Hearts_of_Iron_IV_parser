package mouse.hoi.main.states.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.exception.ReaderException;
import mouse.hoi.main.states.data.VictoryPoint;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.query.DomQueryService;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VictoryPointReader implements DataReader<VictoryPoint> {

    private final DomQueryService queryService;
    @Override
    public Class<VictoryPoint> forType() {
        return VictoryPoint.class;
    }

    @Override
    public VictoryPoint read(DomData domData) {
        List<Integer> integers = queryService.queryList(domData).integerList();
        if (integers.size() != 2) {
            throw new ReaderException("Victory point is expected to be a list of 2 integers, but got " + integers);
        }
        Integer province = integers.getFirst();
        Integer points = integers.getLast();
        VictoryPoint victoryPoint = new VictoryPoint();
        victoryPoint.setProvince(province);
        victoryPoint.setPoints(points);
        return victoryPoint;
    }
}
