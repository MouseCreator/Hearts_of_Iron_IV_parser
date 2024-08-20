package mouse.hoi.main.gfx.io.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.gfx.data.Animation;
import mouse.hoi.main.gfx.data.SpriteType;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.query.DomObjectQuery;
import mouse.hoi.tools.parser.impl.dom.query.DomQueryService;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpriteTypeReader implements DataReader<SpriteType> {

    private final DomQueryService domQueryService;
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
        domObjectQuery.onToken("effectFile").string().set(type::setEffectFile);
        domObjectQuery.onToken("animation").object(Animation.class).push(type::getAnimationList);
        domObjectQuery.onToken("legacy_lazy_load").bool().set(type::setLegacyLazyLoad);
        return type;
    }
}
