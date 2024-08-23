package mouse.hoi.main.character.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.character.data.CharacterData;
import mouse.hoi.main.character.data.GameCharacters;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.DomKV;
import mouse.hoi.tools.parser.impl.dom.DomSimple;
import mouse.hoi.tools.parser.impl.dom.interpreter.InterpreterManager;
import mouse.hoi.tools.parser.impl.dom.query.DomObjectQuery;
import mouse.hoi.tools.parser.impl.dom.query.DomQueryService;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GameCharactersReader implements DataReader<GameCharacters> {

    private final DomQueryService queryService;
    private final InterpreterManager interpreterManager;
    @Override
    public Class<GameCharacters> forType() {
        return GameCharacters.class;
    }

    @Override
    public GameCharacters read(DomData domData) {
        GameCharacters characters = new GameCharacters();
        DomObjectQuery query = queryService.validateAndQueryObject(domData);
        List<DomKV> domKVS = query.allTokens();
        for (DomKV domKV : domKVS) {
            String key = domKV.key().val().stringValue();
            CharacterData characterData = new CharacterData(key);
            interpreterManager.fillObject(domKV.value(), characterData);
        }
        return characters;
    }
}
