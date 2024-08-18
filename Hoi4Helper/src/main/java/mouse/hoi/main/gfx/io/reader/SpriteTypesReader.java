package mouse.hoi.main.gfx.io.reader;

import lombok.AllArgsConstructor;
import mouse.hoi.main.gfx.data.SpriteType;
import mouse.hoi.main.gfx.data.SpriteTypes;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.query.DomObjectQuery;
import mouse.hoi.tools.parser.impl.dom.query.DomQueryService;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import mouse.hoi.tools.parser.impl.reader.helper.Readers;
import mouse.hoi.tools.parser.impl.reader.lr.LeftValue;
import mouse.hoi.tools.parser.impl.reader.lr.RightValue;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SpriteTypesReader implements DataReader<SpriteTypes> {
    private final DomQueryService queryService;
    @Override
    public Class<SpriteTypes> forType() {
        return SpriteTypes.class;
    }


    @Override
    public SpriteTypes read(DomData domData) {
        SpriteTypes spriteTypes = new SpriteTypes();
        DomObjectQuery domObjectQuery = queryService.validateAndQueryObject(domData);
        domObjectQuery.onToken("spriteType").object(SpriteType.class).push(spriteTypes::getSpriteTypeList);
        return spriteTypes;
    }
}
