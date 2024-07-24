package mouse.hoi.tools.parser.impl.ast;

import lombok.Data;

@Data
public class ComplexNode implements Node{
    private KeyValueNode keyValueNode;
    private BlockNode blockNode;

    public ComplexNode(KeyValueNode keyValueNode, BlockNode block) {
        this.keyValueNode = keyValueNode;
        this.blockNode = block;
    }
}
