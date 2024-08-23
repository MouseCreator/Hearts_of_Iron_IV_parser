package mouse.hoi.main.character.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.character.data.CharacterWrapper;
import mouse.hoi.main.character.data.GameCharacters;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.query.DomObjectQuery;
import mouse.hoi.tools.parser.impl.dom.query.DomQueryService;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharactersWrapperReader implements DataReader<CharacterWrapper> {
    private final DomQueryService domQueryService;
    @Override
    public Class<CharacterWrapper> forType() {
        return CharacterWrapper.class;
    }

    @Override
    public CharacterWrapper read(DomData domData) {
        CharacterWrapper wrapper = new CharacterWrapper();
        DomObjectQuery query = domQueryService.validateAndQueryObject(domData);
        query.onToken("characters").forEach().objects(GameCharacters.class).pushAll(wrapper::getCharactersList);
        return wrapper;
    }
}
