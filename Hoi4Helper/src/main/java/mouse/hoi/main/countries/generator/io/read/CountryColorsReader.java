package mouse.hoi.main.countries.generator.io.read;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.countries.generator.data.CountryColors;
import mouse.hoi.main.countries.generator.data.ExtendedColor;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.interpreter.InterpreterManager;
import mouse.hoi.tools.parser.impl.dom.query.DomObjectQuery;
import mouse.hoi.tools.parser.impl.dom.query.DomQueryService;
import mouse.hoi.tools.parser.impl.reader.inits.InitsReader;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryColorsReader implements InitsReader<CountryColors> {
    private final DomQueryService queryService;
    private final InterpreterManager interpreterManager;
    @Override
    public Class<CountryColors> forType() {
        return CountryColors.class;
    }

    @Override
    public void read(CountryColors colors, DomData domData) {
        DomObjectQuery query = queryService.validateAndQueryObject(domData);
        DomData colorObj = query.requireToken("color").requireSingle();
        ExtendedColor color1 = interpreterManager.createObject(colorObj, ExtendedColor.class);
        colors.setColor(color1);
        DomData colorUI = query.requireToken("color_ui").requireSingle();
        ExtendedColor color2 = interpreterManager.createObject(colorUI, ExtendedColor.class);
        colors.setUiColor(color2);
    }
}
