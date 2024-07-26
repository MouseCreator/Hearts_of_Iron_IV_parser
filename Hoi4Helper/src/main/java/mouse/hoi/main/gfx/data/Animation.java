package mouse.hoi.main.gfx.data;

import lombok.Data;

import mouse.hoi.tools.parser.data.DPos;

@Data
public class Animation {
    private String animationMaskFile;
    private String animationTextureFile;
    private double animationRotation;
    private boolean looping;
    private double animationTime;
    private double delaySeconds;
    private String blendMode;
    private String type;
    private DPos rotationOffset;
    private DPos textureScale;
}
