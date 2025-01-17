package mouse.hoi.tools.parser.impl.token.mapper.creator;

import mouse.hoi.tools.parser.data.GameDate;
import mouse.hoi.tools.parser.generator.TokenType;
import mouse.hoi.tools.parser.impl.token.DateToken;
import mouse.hoi.tools.parser.impl.token.Location;
import mouse.hoi.tools.parser.impl.token.Token;
import mouse.hoi.tools.parser.impl.token.mapper.MappingToken;
import org.springframework.stereotype.Service;

@Service
public class DateTokenCreator implements TokenCreator{
    @Override
    public Token createToken(MappingToken mappingToken) {
        Location location = mappingToken.getLocation();
        String value = mappingToken.getValue();
        String[] split = value.split("\\.");
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        int day = Integer.parseInt(split[2]);
        int hour = 0;
        if (split.length > 3) {
            hour = Integer.parseInt(split[3]);
        }
        GameDate gameDate = new GameDate(year, month, day, hour);
        return new DateToken(location, gameDate);
    }

    @Override
    public TokenType byType() {
        return TokenType.DATE;
    }
}
