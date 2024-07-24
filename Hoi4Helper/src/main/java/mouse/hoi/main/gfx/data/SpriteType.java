package mouse.hoi.main.gfx.data;

import lombok.Data;
import mouse.hoi.tools.parser.annotation.*;
import mouse.hoi.tools.parser.impl.interpreter.simple.Inits;

import java.util.ArrayList;
import java.util.List;

@Data
@GameObj
public class SpriteType implements Inits {
    @WriteAs
    @Quoted
    private String name;
    @WriteAs("texturefile")
    @Quoted
    private String textureFile;
    @WriteAs("effectFile")
    @Quoted
    private String effectFile;
    @WriteAs("animation")
    @SkipIfEmpty
    private List<Animation> animationList;
    @WriteAs("legacy_lazy_load")
    @SkipIf("yes")
    private boolean legacyLazyLoad;
    @Override
    public void initialize() {
        animationList = new ArrayList<>();
    }
}
