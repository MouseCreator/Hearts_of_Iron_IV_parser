package mouse.hoi.main.gfx.io.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.gfx.data.SpriteTypes;
import mouse.hoi.main.gfx.data.SpriteTypesWrapper;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.query.DomObjectQuery;
import mouse.hoi.tools.parser.impl.dom.query.DomQueryService;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpriteTypesWrapperReader implements DataReader<SpriteTypesWrapper> {

    @Override
    public Class<SpriteTypesWrapper> forType() {
        return SpriteTypesWrapper.class;
    }
    private final DomQueryService queryService;
    @Override
    public SpriteTypesWrapper read(DomData domData) {
        SpriteTypesWrapper spriteTypesWrapper = new SpriteTypesWrapper();
        DomObjectQuery query = queryService.validateAndQueryObject(domData);
        query.onToken("spriteTypes").object(SpriteTypes.class).push(spriteTypesWrapper::getSpriteTypes);
        return spriteTypesWrapper;
    }
}
