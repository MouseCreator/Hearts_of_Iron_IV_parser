package mouse.hoi.main.common.framework;


import lombok.RequiredArgsConstructor;
import mouse.hoi.exception.EffectException;
import mouse.hoi.main.common.data.effect.store.EffectData;
import mouse.hoi.main.common.data.effect.store.EffectDataList;
import mouse.hoi.main.common.data.effect.store.EffectDataObj;
import mouse.hoi.main.common.data.effect.store.EffectDataSimple;
import mouse.hoi.tools.parser.impl.ast.BlockNode;
import mouse.hoi.tools.parser.impl.ast.KeyValueNode;
import mouse.hoi.tools.parser.impl.ast.Node;
import mouse.hoi.tools.parser.impl.ast.SimpleNode;
import mouse.hoi.tools.parser.impl.reader.NodeMapper;
import mouse.hoi.tools.parser.impl.reader.lr.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EffectReaderHelper {
    private final NodeMapper nodeMapper;
    public EffectData read(RightValue rightValue) {
        if (rightValue.isBlock()) {
            return parseBlock(rightValue.blockValue());
        } else {
            return parseSimple(rightValue);
        }
    }

    private EffectData parseSimple(RightValue rightValue) {
        EffectDataSimple effectDataSimple = new EffectDataSimple();
        if (rightValue.isInteger()) {
            effectDataSimple.putSimple(rightValue.intValue());
        } else if (rightValue.isDouble()) {
            effectDataSimple.putSimple(rightValue.doubleValue());
        } else if (rightValue.isBoolean()) {
            effectDataSimple.putSimple(rightValue.boolValue());
        } else {
            effectDataSimple.putSimple(rightValue.stringValue());
        }
        return effectDataSimple;
    }

    private EffectData parseBlock(BlockNode blockNode) {
        List<Node> children = blockNode.getChildren();
        if (children.isEmpty()) {
            return new EffectDataObj();
        }
        Node first = children.getFirst();
        boolean isList = first instanceof SimpleNode;
        if (isList) {
            return parseListBlock(children);
        } else {
            return parseObjectBlock(children);
        }
    }

    private EffectDataObj parseObjectBlock(List<Node> children) {
        EffectDataObj effectDataObj = new EffectDataObj();
        for (Node child : children) {
            if (child instanceof KeyValueNode kn) {
                LeftRightValue lr = nodeMapper.createLeftRightValue(kn);
                LeftValue leftValue = lr.getLeftValue();
                String key = leftValue.stringValue();
                RightValue rightValue = lr.getRightValue();
                if (rightValue.isBlock()) {
                    EffectData effectData = parseBlock(rightValue.blockValue());
                    effectDataObj.put(key, effectData);
                } else {
                    if (rightValue.isBoolean()) {
                        effectDataObj.put(key, rightValue.boolValue());
                    } else if (rightValue.isInteger()) {
                        effectDataObj.put(key, rightValue.intValue());
                    } else if (rightValue.isDouble()) {
                        effectDataObj.put(key, rightValue.doubleValue());
                    } else {
                        effectDataObj.put(key, rightValue.stringValue());
                    }
                }
            } else {
                throw new EffectException("Unexpected node for effect: " + child);
            }
        }
        return effectDataObj;
    }

    private EffectDataList parseListBlock(List<Node> children) {
        EffectDataList effectDataList = new EffectDataList();
        for (Node child : children) {
            if (child instanceof SimpleNode sn) {
                SimpleValue simple = nodeMapper.createSimple(sn);
                if (simple.isBoolean()) {
                    effectDataList.add(simple.boolValue());
                } else if (simple.isInteger()) {
                    effectDataList.add(simple.intValue());
                } else if (simple.isDouble()) {
                    effectDataList.add(simple.doubleValue());
                } else {
                    effectDataList.add(simple.stringValue());
                }
            } else {
                throw new EffectException("Unexpected node for effect: " + child);
            }
        }
        return effectDataList;
    }
}
