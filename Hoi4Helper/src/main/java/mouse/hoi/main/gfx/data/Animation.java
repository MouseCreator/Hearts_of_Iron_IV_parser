package mouse.hoi.main.gfx.data;

import lombok.Data;
import mouse.hoi.tools.parser.annotation.GameObj;
import mouse.hoi.tools.parser.annotation.Quoted;
import mouse.hoi.tools.parser.annotation.WriteAs;
import mouse.hoi.tools.parser.data.DPos;

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
    private DPos rotationOffset;
    @WriteAs("animationtexturescale")
    private DPos textureScale;
}
