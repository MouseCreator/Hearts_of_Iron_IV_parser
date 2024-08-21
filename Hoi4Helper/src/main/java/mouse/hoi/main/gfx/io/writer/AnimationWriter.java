package mouse.hoi.main.gfx.io.writer;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.gfx.data.Animation;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.parser.impl.writer.dw.DWData;
import mouse.hoi.tools.parser.impl.writer.support.DWObjectBuilder;
import mouse.hoi.tools.parser.impl.writer.support.WriterSupport;
import mouse.hoi.tools.parser.impl.writer.style.DoubleStyle;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;
import mouse.hoi.tools.parser.impl.writer.style.StringStyle;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnimationWriter implements DataWriter<Animation> {

    private final WriterSupport writerSupport;
    @Override
    public Class<Animation> forType() {
        return Animation.class;
    }


    @Override
    public DWData write(Animation animation, ObjectStyle style) {
        DWObjectBuilder b = writerSupport.build(style);
        b.key("animationmaskfile").string(animation::getAnimationMaskFile, StringStyle.QUOTED);
        b.key("animationtexturefile").string(animation::getAnimationTextureFile, StringStyle.QUOTED);
        b.key("animationrotation").dbl(animation::getAnimationRotation, DoubleStyle.min(1));
        b.key("animationlooping").bool(animation::isLooping);
        b.key("animationtime").dbl(animation::getAnimationTime);
        b.key("animationdelay").dbl(animation::getDelaySeconds);
        b.key("animationblendmode").string(animation::getBlendMode, StringStyle.QUOTED);
        b.key("animationtype").string(animation::getType, StringStyle.QUOTED);
        b.key("animationrotationoffset").object(animation::getRotationOffset, ObjectStyle.ONE_LINE);
        b.key("animationtexturescale").object(animation::getTextureScale, ObjectStyle.ONE_LINE);
        return b.get();
    }
}
