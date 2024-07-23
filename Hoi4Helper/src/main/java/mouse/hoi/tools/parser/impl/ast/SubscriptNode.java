package mouse.hoi.tools.parser.impl.ast;

import lombok.Data;

@Data
public class SubscriptNode implements Node {
    private SimpleNode root;
    private Node subscript;
}
