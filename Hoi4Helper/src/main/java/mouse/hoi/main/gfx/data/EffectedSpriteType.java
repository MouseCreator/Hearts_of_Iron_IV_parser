package mouse.hoi.main.gfx.data;

import lombok.Data;
import mouse.hoi.tools.parser.annotation.WriteAs;

import java.util.List;

@Data
public class EffectedSpriteType {
    @WriteAs
    private String name;
    @WriteAs("texturefile")
    private String textureFile;
    @WriteAs("effectFile")
    private String effectFile;
    @WriteAs("animation")
    private List<Animation> animationList;
    @WriteAs("legacy_lazy_load")
    private boolean legacyLazyLoad;
}
