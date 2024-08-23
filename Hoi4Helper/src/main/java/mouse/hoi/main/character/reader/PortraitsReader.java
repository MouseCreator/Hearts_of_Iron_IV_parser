package mouse.hoi.main.character.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.character.data.PortraitType;
import mouse.hoi.main.character.data.Portraits;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.DomKV;
import mouse.hoi.tools.parser.impl.dom.DomSimple;
import mouse.hoi.tools.parser.impl.dom.interpreter.InterpreterManager;
import mouse.hoi.tools.parser.impl.dom.query.DomObjectQuery;
import mouse.hoi.tools.parser.impl.dom.query.DomQueryService;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PortraitsReader implements DataReader<Portraits> {
    private final DomQueryService queryService;
    private final InterpreterManager interpreterManager;
    @Override
    public Class<Portraits> forType() {
        return Portraits.class;
    }

    @Override
    public Portraits read(DomData domData) {
        DomObjectQuery query = queryService.validateAndQueryObject(domData);
        List<DomKV> domKVS = query.allTokens();
        Portraits portraits = new Portraits();
        for (DomKV domKV : domKVS) {
            String key = domKV.key().val().stringValue();
            PortraitType portraitType = new PortraitType(key);
            interpreterManager.fillObject(domKV.value(), portraitType);
            portraits.getPortraitTypeList().add(portraitType);
        }
        return portraits;
    }
}
