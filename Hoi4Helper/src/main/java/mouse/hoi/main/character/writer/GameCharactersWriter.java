package mouse.hoi.main.character.writer;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.character.data.CharacterData;
import mouse.hoi.main.character.data.GameCharacters;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.parser.impl.writer.dw.DWData;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;
import mouse.hoi.tools.parser.impl.writer.support.DWObjectBuilder;
import mouse.hoi.tools.parser.impl.writer.support.WriterSupport;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GameCharactersWriter implements DataWriter<GameCharacters> {

    private final WriterSupport writerSupport;
    @Override
    public Class<GameCharacters> forType() {
        return GameCharacters.class;
    }

    @Override
    public DWData write(GameCharacters object, ObjectStyle style) {
        DWObjectBuilder b = writerSupport.build(style);
        List<CharacterData> characterList = object.getCharacterList();
        for (CharacterData data : characterList) {
            b.key(data.getName()).objectRaw(data);
        }
        return b.get();
    }
}
