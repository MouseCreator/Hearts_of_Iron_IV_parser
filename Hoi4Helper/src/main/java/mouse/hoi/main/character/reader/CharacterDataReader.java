package mouse.hoi.main.character.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.character.data.*;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.query.DomObjectQuery;
import mouse.hoi.tools.parser.impl.dom.query.DomQueryService;
import mouse.hoi.tools.parser.impl.reader.inits.InitsReader;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CharacterDataReader implements InitsReader<CharacterData> {

    private final DomQueryService domQueryService;
    @Override
    public Class<CharacterData> forType() {
        return CharacterData.class;
    }

    @Override
    public void read(CharacterData charData, DomData domData) {
        DomObjectQuery query = domQueryService.validateAndQueryObject(domData);
        query.onToken("name").string().setOrSkip(charData::setName);
        query.onToken("portraits").object(Portraits.class).setOrSkip(charData::setPortraits);
        putRole(charData, query, "country_leader", RoleCountryLeader.class);
        putRole(charData, query, "advisor", RoleAdvisor.class);
        putRole(charData, query, "corps_commander", RoleCorpsCommander.class);
        putRole(charData, query, "field_marshal", RoleFieldMarshal.class);
        putRole(charData, query, "navy_leader", RoleNavyLeader.class);
    }

    private static void putRole(CharacterData charData, DomObjectQuery query, String role, Class<? extends CharacterRole> c) {
        query.onToken(role).object(c)
                .optional().ifPresent(t -> charData.getCharacterRoleList().add(t));
    }
}
