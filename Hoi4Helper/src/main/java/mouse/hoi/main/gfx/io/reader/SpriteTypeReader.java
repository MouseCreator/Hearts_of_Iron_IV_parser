package mouse.hoi.main.gfx.io.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.gfx.data.Animation;
import mouse.hoi.main.gfx.data.SpriteType;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.interpreter.InterpreterManager;
import mouse.hoi.tools.parser.impl.dom.query.DomObjectQuery;
import mouse.hoi.tools.parser.impl.dom.query.DomQueryService;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpriteTypeReader implements DataReader<SpriteType> {

    private final DomQueryService domQueryService;
    private final InterpreterManager interpreterManager;
    @Override
    public Class<SpriteType> forType() {
        return SpriteType.class;
    }

    @Override
    public SpriteType read(DomData domData) {
        SpriteType type = new SpriteType();
        DomObjectQuery domObjectQuery = domQueryService.validateAndQueryObject(domData);
        domObjectQuery.requireToken("name").string().set(type::setName);
        domObjectQuery.requireToken("texturefile").string().set(type::setTextureFile);
        domObjectQuery.onToken("effectFile").string().setOrSkip(type::setEffectFile);
        List<DomData> animations = domObjectQuery.onToken("animation").list();
        for (DomData a : animations) {
            Animation object = interpreterManager.createObject(a, Animation.class);
            type.getAnimationList().add(object);
        }
        domObjectQuery.onToken("legacy_lazy_load").bool().setOrSkip(type::setLegacyLazyLoad);
        return type;
    }
}
