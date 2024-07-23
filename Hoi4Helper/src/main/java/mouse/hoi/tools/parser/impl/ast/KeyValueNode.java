package mouse.hoi.tools.parser.impl.ast;

import lombok.Data;

@Data
public class KeyValueNode implements Node {
    private Node key;
    private Node value;
}
