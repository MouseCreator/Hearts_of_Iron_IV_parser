package mouse.hoi.main.character.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.character.data.RoleCountryLeader;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.query.DomObjectQuery;
import mouse.hoi.tools.parser.impl.dom.query.DomQueryService;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleCountryLeaderReader implements DataReader<RoleCountryLeader> {
    private final DomQueryService domQueryService;
    @Override
    public Class<RoleCountryLeader> forType() {
        return RoleCountryLeader.class;
    }

    @Override
    public RoleCountryLeader read(DomData domData) {
        RoleCountryLeader leader = new RoleCountryLeader();
        DomObjectQuery query = domQueryService.validateAndQueryObject(domData);
        query.onToken("ideology").string().setOrSkip(leader::setIdeology);
        query.onToken("expire").string().setOrSkip(leader::setExpire);
        query.onToken("id").integer().setOrSkip(leader::setId);
        query.onToken("traits").stringList().setOrSkip(leader::setTraits);
        return leader;
    }
}
