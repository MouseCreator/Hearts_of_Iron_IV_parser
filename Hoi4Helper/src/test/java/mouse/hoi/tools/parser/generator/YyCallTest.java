package mouse.hoi.tools.parser.generator;

import mouse.hoi.tools.parser.generator.help.YyResultBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class YyCallTest {
    private YyCall yyCall;

    @BeforeEach
    void setUp() {
        yyCall = new YyCall();
    }

    @Test
    void call_Simple() {
        String content = """
                a = {
                    b=3.14
                }
                """;
        YyResult actual = yyCall.read(content);
        YyResult expected = YyResultBuilder.build()
                .id("a").space().special("=").space().special("{").ln()
                .space(4).id("b").special("=").doubleVal(3.14).ln()
                .special("}").ln().get();
        testEquals(actual, expected);
    }

    private void testEquals(YyResult actual, YyResult expected) {
        assertEquals(actual.filename(), expected.filename());
        List<Yytoken> aList = actual.yytokenList();
        List<Yytoken> eList = expected.yytokenList();
        assertEquals(eList.size(), aList.size());
        int size = eList.size();
        List<Yytoken> differentTokensA = new ArrayList<>();
        List<Yytoken> differentTokensE = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Yytoken eToken = eList.get(i);
            Yytoken aToken = aList.get(i);
            if (!eToken.equals(aToken)) {
                differentTokensA.add(aToken);
                differentTokensE.add(eToken);
            }
        }
        int differences = differentTokensA.size();
        if (differences == 0) {
            return;
        }
        StringBuilder builder = new StringBuilder("Mismatch tokens:");
        builder.append("\nExpected || Actual");
        for (int i = 0; i < differences; i++) {
            Yytoken dif1 = differentTokensE.get(i);
            Yytoken dif2 = differentTokensA.get(i);
            builder.append("\n").append(dif1).append("\t\t||\t\t").append(dif2);
        }
        fail(builder.toString());
    }

    private void printList(List<Yytoken> list) {
        for (Yytoken yytoken : list){
            System.out.println(yytoken);
        }
    }
}