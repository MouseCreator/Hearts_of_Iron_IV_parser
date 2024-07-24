package mouse.hoi.main.gfx.data;

import lombok.Data;
import mouse.hoi.tools.parser.annotation.GameObj;
import mouse.hoi.tools.parser.annotation.Quoted;
import mouse.hoi.tools.parser.annotation.StyleHint;
import mouse.hoi.tools.parser.annotation.WriteAs;
import mouse.hoi.tools.parser.data.DPos;
import mouse.hoi.tools.parser.impl.writer.WriteStyle;

@Data
@GameObj
public class Animation {
    @Quoted
    @WriteAs("animationmaskfile")
    private String animationMaskFile;
    @WriteAs("animationtexturefile")
    @Quoted
    private String animationTextureFile;
    @WriteAs
    private double animationRotation;
    @WriteAs("animationlooping")
    private boolean looping;
    @WriteAs
    private double animationTime;
    @WriteAs("animationdelay")
    private double delaySeconds;
    @Quoted
    @WriteAs("animationblendmode")
    private String blendMode;
    @Quoted
    @WriteAs("animationtype")
    private String type;
    @WriteAs("animationrotationoffset")
    @StyleHint(WriteStyle.SIMPLE)
    private DPos rotationOffset;
    @WriteAs("animationtexturescale")
    @StyleHint(WriteStyle.SIMPLE)
    private DPos textureScale;
}
