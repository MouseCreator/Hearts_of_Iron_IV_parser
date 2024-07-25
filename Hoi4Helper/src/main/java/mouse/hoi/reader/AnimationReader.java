package mouse.hoi.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.gfx.data.Animation;
import mouse.hoi.tools.parser.data.DPos;
import mouse.hoi.tools.parser.impl.ast.BlockNode;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import mouse.hoi.tools.parser.impl.reader.helper.Readers;
import mouse.hoi.tools.parser.impl.reader.lr.LeftValue;
import mouse.hoi.tools.parser.impl.reader.lr.RightValue;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnimationReader implements DataReader<Animation> {

    private final Readers readers;
    @Override
    public Class<Animation> forType() {
        return Animation.class;
    }

    @Override
    public void onKeyValue(Animation object, LeftValue leftValue, RightValue rightValue) {
        readers.lrValues().with(leftValue, rightValue)
                .onToken("animationmaskfile").setString(object::setAnimationMaskFile)
                .onToken("animationtexturefile").setString(object::setAnimationTextureFile)
                .onToken("animationrotation").setDouble(object::setAnimationRotation)
                .onToken("animationtime").setDouble(object::setAnimationTime)
                .onToken("animationdelay").setDouble(object::setDelaySeconds)
                .onToken("animationblendmode").setString(object::setBlendMode)
                .onToken("animationtype").setString(object::setType)
                .onToken("animationrotationoffset").mapBlock(this::block).consume(object::setRotationOffset)
                .onToken("animationtexturescale").mapBlock(this::block).consume(object::setRotationOffset)
                .orElseThrow();
    }

    private DPos block(BlockNode blockNode) {
        return readers.interpreters().read(DPos.class, blockNode);
    }
}
