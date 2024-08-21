package mouse.hoi.main.countries.generator.io.read;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.countries.generator.data.ExtendedColor;
import mouse.hoi.main.countries.generator.data.RGBColor;
import mouse.hoi.tools.parser.impl.dom.DomComplex;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.query.DomQueryService;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExtendedColorReader implements DataReader<ExtendedColor> {

    private final DomQueryService domQueryService;
    @Override
    public Class<ExtendedColor> forType() {
        return ExtendedColor.class;
    }

    @Override
    public ExtendedColor read(DomData domData) {
        DomComplex complex = domData.complex();
        String prefix = complex.val().prefix().stringValue();
        ExtendedColor ec = new ExtendedColor();
        ec.setPrefix(prefix);
        DomData colorData = complex.val().domData();
        List<Integer> integers = domQueryService.queryList(colorData).integerList();
        RGBColor color = new RGBColor();
        color.setR(integers.get(0));
        color.setG(integers.get(1));
        color.setB(integers.get(2));
        ec.setRgbColor(color);
        return ec;
    }
}
