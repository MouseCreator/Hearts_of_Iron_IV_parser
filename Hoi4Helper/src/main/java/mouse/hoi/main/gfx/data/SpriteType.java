package mouse.hoi.main.gfx.data;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SpriteType {
    private String name;
    private String textureFile;
    private String effectFile;
    private List<Animation> animationList;
    private boolean legacyLazyLoad;

    public SpriteType() {
        animationList = new ArrayList<>();
    }
}
