package mouse.hoi.tools.parser.impl.reader.lr;

import lombok.Data;
import lombok.EqualsAndHashCode;
import mouse.hoi.tools.parser.impl.ast.BlockNode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BlockValue extends AbstractPossibleValue{
    private final BlockNode blockNode;

    @Override
    public boolean isBlock() {
        return true;
    }

    @Override
    public BlockNode blockValue() {
        return blockNode;
    }

    @Override
    public String stringValue() {
        return blockNode.toString();
    }
}
