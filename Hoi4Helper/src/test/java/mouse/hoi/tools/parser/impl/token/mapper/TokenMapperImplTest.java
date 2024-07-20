package mouse.hoi.tools.parser.impl.token.mapper;

import mouse.hoi.tools.context.Context;
import mouse.hoi.tools.parser.generator.TokenType;
import mouse.hoi.tools.parser.generator.Yytoken;
import mouse.hoi.tools.parser.impl.error.ScanException;
import mouse.hoi.tools.parser.impl.token.DoubleToken;
import mouse.hoi.tools.parser.impl.token.IntegerToken;
import mouse.hoi.tools.parser.impl.token.Location;
import mouse.hoi.tools.parser.impl.token.Token;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenMapperImplTest {

    private TokenMapper tokenMapper;

    @BeforeEach
    void setUp() {
        tokenMapper = Context.get().getBean(TokenMapperImpl.class);
    }

    @Test
    void mapIntToken_valid() {
        String file = "a.txt";
        Token intToken = tokenMapper.mapToToken(file, new Yytoken(TokenType.INT, "234", 0, 0));
        assertEquals(intToken, new IntegerToken(Location.of(file, 1, 1), 234));
    }



    @Test
    void mapIntToken_invalid() {
        String file = "b.txt";
        assertThrows(ScanException.class, () -> tokenMapper.mapToToken(file, new Yytoken(TokenType.INT, "HELLO", 0, 0)));
    }

    @Test
    void mapDoubleToken() {
        String file = "a.txt";
        DoubleToken expected = new DoubleToken(Location.of(file, 1, 1), 12);
        Token t1 = tokenMapper.mapToToken(file, new Yytoken(TokenType.DOUBLE, "12", 0, 0));
        assertEquals(expected, t1);

        t1 = tokenMapper.mapToToken(file, new Yytoken(TokenType.DOUBLE, "12.0000", 0, 0));
        assertEquals(expected, t1);

        assertThrows(ScanException.class, ()-> tokenMapper.mapToToken(file, new Yytoken(TokenType.DOUBLE, "HELLO", 0, 0)));
        assertThrows(ScanException.class, ()-> tokenMapper.mapToToken(file, new Yytoken(TokenType.DOUBLE, "12,0", 0, 0)));
        assertThrows(ScanException.class, ()-> tokenMapper.mapToToken(file, new Yytoken(TokenType.DOUBLE, "12,", 0, 0)));
    }

    @Test
    void invalidToken_null() {
        assertThrows(ScanException.class, ()-> tokenMapper.mapToToken("", new Yytoken(null, "", 0, 0)));
    }
}