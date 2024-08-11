package mouse.hoi.main.gfx.io.reader;

import lombok.AllArgsConstructor;
import mouse.hoi.main.gfx.data.SpriteType;
import mouse.hoi.main.gfx.data.SpriteTypes;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import mouse.hoi.tools.parser.impl.reader.helper.Readers;
import mouse.hoi.tools.parser.impl.reader.lr.LeftValue;
import mouse.hoi.tools.parser.impl.reader.lr.RightValue;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SpriteTypesReader implements DataReader<SpriteTypes> {
    private final Readers readers;
    @Override
    public Class<SpriteTypes> forType() {
        return SpriteTypes.class;
    }

    @Override
    public void onKeyValue(SpriteTypes object, LeftValue leftValue, RightValue rightValue) {
        readers.lrValues().with(leftValue, rightValue)
                .onToken("spriteType")
                    .mapBlock(b->readers.interpreters().read(SpriteType.class, b))
                    .consume(t -> object.getSpriteTypeList().add(t))
                .orElseThrow();
    }
}
