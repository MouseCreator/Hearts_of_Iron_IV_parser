package mouse.hoi.tools.parser.impl.reader.lr;

import mouse.hoi.tools.parser.impl.ast.BlockNode;

public interface RightValue extends PossibleValue {
    boolean isBlock();
    BlockNode blockValue();
}
