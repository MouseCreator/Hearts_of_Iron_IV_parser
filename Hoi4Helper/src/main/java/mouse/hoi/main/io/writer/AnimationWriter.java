package mouse.hoi.main.io.writer;

import mouse.hoi.main.gfx.data.Animation;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.parser.impl.writer.SpecialWriter;
import mouse.hoi.tools.parser.impl.writer.style.CommonStyles;
import mouse.hoi.tools.parser.impl.writer.style.DoubleStyle;
import mouse.hoi.tools.parser.impl.writer.style.StringStyle;
import org.springframework.stereotype.Service;

@Service
public class AnimationWriter implements DataWriter<Animation> {
    @Override
    public Class<Animation> forType() {
        return Animation.class;
    }

    @Override
    public void write(SpecialWriter writer, Animation object) {
        writer
                .key("animationmaskfile").value(object::getAnimationMaskFile, StringStyle.QUOTED).ln()
                .key("animationtexturefile").value(object::getAnimationTextureFile, StringStyle.QUOTED).ln()
                .key("animationrotation").value(object::getAnimationRotation, DoubleStyle.min(1)).ln()
                .key("animationlooping").valueBoolean(object::isLooping).ln()
                .key("animationtime").valueDouble(object::getAnimationTime).ln()
                .key("animationdelay").valueDouble(object::getDelaySeconds).ln()
                .key("animationblendmode").value(object::getBlendMode, StringStyle.QUOTED).ln()
                .key("animationtype").value(object::getType, StringStyle.QUOTED).ln()
                .key("animationrotationoffset").object(object::getRotationOffset, CommonStyles.SIMPLE).ln()
                .key("animationtexturescale").object(object::getTextureScale, CommonStyles.SIMPLE).ln();
    }
}
