package mouse.hoi.main.character.writer;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.character.data.CharacterWrapper;
import mouse.hoi.main.character.data.GameCharacters;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.parser.impl.writer.dw.DWData;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;
import mouse.hoi.tools.parser.impl.writer.support.DWObjectBuilder;
import mouse.hoi.tools.parser.impl.writer.support.WriterSupport;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterWrapperWriter implements DataWriter<CharacterWrapper> {
    private final WriterSupport writerSupport;
    @Override
    public Class<CharacterWrapper> forType() {
        return CharacterWrapper.class;
    }

    @Override
    public DWData write(CharacterWrapper object, ObjectStyle style) {
        DWObjectBuilder b = writerSupport.build(style);
        DWObjectBuilder s = writerSupport.build(style);
        b.listAll("characters").objects(object::getCharactersList);
        return b.get();
    }
}
