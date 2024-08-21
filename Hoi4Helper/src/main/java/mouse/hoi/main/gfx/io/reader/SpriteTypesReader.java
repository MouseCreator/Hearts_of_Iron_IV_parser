package mouse.hoi.main.gfx.io.reader;

import lombok.AllArgsConstructor;
import mouse.hoi.main.gfx.data.SpriteType;
import mouse.hoi.main.gfx.data.SpriteTypes;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.interpreter.InterpreterManager;
import mouse.hoi.tools.parser.impl.dom.query.DomObjectQuery;
import mouse.hoi.tools.parser.impl.dom.query.DomQueryService;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SpriteTypesReader implements DataReader<SpriteTypes> {
    private final DomQueryService queryService;
    private final InterpreterManager interpreterManager;
    @Override
    public Class<SpriteTypes> forType() {
        return SpriteTypes.class;
    }


    @Override
    public SpriteTypes read(DomData domData) {
        SpriteTypes spriteTypes = new SpriteTypes();
        DomObjectQuery domObjectQuery = queryService.validateAndQueryObject(domData);
        List<DomData> spriteTypeList = domObjectQuery.onToken("spriteType").list();
        spriteTypeList.forEach(s -> {
            SpriteType st = interpreterManager.createObject(s, SpriteType.class);
            spriteTypes.getSpriteTypeList().add(st);
        });
        return spriteTypes;
    }
}
