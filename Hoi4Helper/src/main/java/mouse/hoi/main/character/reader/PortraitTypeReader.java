package mouse.hoi.main.character.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.character.data.PortraitType;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.query.DomObjectQuery;
import mouse.hoi.tools.parser.impl.dom.query.DomQueryService;
import mouse.hoi.tools.parser.impl.reader.inits.InitsReader;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PortraitTypeReader implements InitsReader<PortraitType> {

    private final DomQueryService domQueryService;
    @Override
    public Class<PortraitType> forType() {
        return PortraitType.class;
    }

    @Override
    public void read(PortraitType type, DomData domData) {
        DomObjectQuery query = domQueryService.validateAndQueryObject(domData);
        query.onToken("large").string().setOrSkip(type::setLarge);
        query.onToken("small").string().setOrSkip(type::setSmall);
    }
}
