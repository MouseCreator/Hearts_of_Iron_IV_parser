package mouse.hoi.main.gfx.service.synch;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.gfx.data.Animation;
import mouse.hoi.main.gfx.data.SpriteType;
import mouse.hoi.tools.files.PathManager;
import mouse.hoi.main.gfx.service.properties.GFXType;
import mouse.hoi.main.gfx.service.properties.GfxProperties;
import mouse.hoi.main.gfx.service.SpriteTypesSynchronizer;
import mouse.hoi.tools.parser.data.DPos;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShinesSpriteTypesSynch implements Synchronizer {
    private final SpriteTypesSynchronizer spriteTypesSynchronizer;
    private final PathManager pathManager;

    public void sync(GfxProperties gfxProperties) {
        String gfxFile = gfxProperties.getGoalShinesFile();
        String directory = gfxProperties.getGoalDirectory();
        spriteTypesSynchronizer.synchronizeAndWrite(gfxFile, directory, this::createGoalType);
    }
    private SpriteType createGoalType(String file) {
        SpriteType spriteType = new SpriteType();
        String name = pathManager.build(file).name().noExtension().get();
        name = "GFX_" + name + "_shine";
        String fileShort = pathManager.build(file).dir("gfx").get();
        spriteType.setName(name);
        spriteType.setTextureFile(fileShort);
        spriteType.setEffectFile("gfx/FX/buttonstate.lua");

        Animation animation1 = new Animation();
        animation1.setAnimationMaskFile(fileShort);
        animation1.setAnimationTextureFile("gfx/interface/goals/shine_overlay.dds");
        animation1.setAnimationRotation(-90.0);
        animation1.setLooping(false);
        animation1.setAnimationTime(0.75);
        animation1.setBlendMode("add");
        animation1.setType("scrolling");
        animation1.setRotationOffset(DPos.of(0, 0));
        animation1.setTextureScale(DPos.of(1, 1));

        Animation animation2 = new Animation();

        animation2.setAnimationMaskFile(fileShort);
        animation2.setAnimationTextureFile("gfx/interface/goals/shine_overlay.dds");
        animation2.setAnimationRotation(90.0);
        animation2.setLooping(false);
        animation2.setAnimationTime(0.75);
        animation2.setBlendMode("add");
        animation2.setType("scrolling");
        animation2.setRotationOffset(DPos.of(0, 0));
        animation2.setTextureScale(DPos.of(1, 1));

        spriteType.setAnimationList(List.of(animation1, animation2));
        spriteType.setLegacyLazyLoad(false);

        return spriteType;
    }

    @Override
    public GFXType forType() {
        return GFXType.GOAL_SHINE;
    }
}
