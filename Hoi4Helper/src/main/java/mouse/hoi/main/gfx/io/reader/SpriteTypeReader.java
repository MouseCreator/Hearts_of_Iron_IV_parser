package mouse.hoi.main.gfx.io.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.gfx.data.Animation;
import mouse.hoi.main.gfx.data.SpriteType;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import mouse.hoi.tools.parser.impl.reader.helper.Readers;
import mouse.hoi.tools.parser.impl.reader.lr.LeftValue;
import mouse.hoi.tools.parser.impl.reader.lr.RightValue;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpriteTypeReader implements DataReader<SpriteType> {

    private final Readers readers;
    @Override
    public Class<SpriteType> forType() {
        return SpriteType.class;
    }

    @Override
    public void onKeyValue(SpriteType object, LeftValue leftValue, RightValue rightValue) {
        readers.lrValues().with(leftValue, rightValue)
                .onToken("name").setString(object::setName)
                .onToken("texturefile").setString(object::setTextureFile)
                .onToken("effectFile").setString(object::setEffectFile)
                .onToken("animation").mapBlock(b -> readers.interpreters().read(Animation.class, b)).push(object::getAnimationList)
                .onToken("legacy_lazy_load").setBoolean(object::setLegacyLazyLoad)
                .orElseThrow();
    }
}
