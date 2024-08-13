package mouse.hoi.tools.parser.impl.reader.helper;


import mouse.hoi.tools.parser.impl.ast.BlockNode;
import mouse.hoi.tools.parser.impl.ast.Node;
import mouse.hoi.tools.parser.impl.reader.engine.ReaderEngine;
import mouse.hoi.tools.parser.impl.reader.lr.LeftRightValue;

import java.util.List;

public interface Interpreters {
    <T> T read(Class<T> clazz, Node node);
    void setEngine(ReaderEngine engine);
    List<LeftRightValue> getLeftRightValues(BlockNode blockNode);
}
