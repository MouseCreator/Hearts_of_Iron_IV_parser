package mouse.hoi.tools.parser.impl.bind;

import mouse.hoi.tools.context.Context;
import mouse.hoi.tools.parser.impl.ast.AbstractSyntaxTree;
import mouse.hoi.tools.parser.impl.printer.TreePrinter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ScanParseBinderTest {

    private ScanParseBinder binder;

    private TreePrinter treePrinter;

    @BeforeEach
    void setUp() {
        binder = Context.get().getBean(ScanParseBinder.class);
        treePrinter = Context.get().getBean(TreePrinter.class);
    }

    @Test
    void createTree_Simple() {
        String simpleContent = """
                a = b
                c = {
                    d = 13
                }
                e = 3.45
                """;
        String adjusted = adjust(simpleContent);
        AbstractSyntaxTree tree = binder.createTreeFromContent(simpleContent);
        Assertions.assertNotNull(tree);
        Assertions.assertEquals(3, tree.root().getChildren().size());
        String s = treePrinter.printTree(tree);
        Assertions.assertEquals(adjusted, s);
    }

    private String adjust(String content) {
        StringBuilder builder = new StringBuilder();
        String[] split = content.split("\n");
        for (String s : split) {
            s = s.replace("    ", "\t");
            builder.append(s).append("\n");
        }
        return builder.toString();
    }
}