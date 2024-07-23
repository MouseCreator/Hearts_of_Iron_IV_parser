package mouse.hoi.tools.parser.impl.ast;

import lombok.Data;

@Data
public class ComparisonNode implements Node{
    private Node key;
    private Node value;
    private String sign;
}
