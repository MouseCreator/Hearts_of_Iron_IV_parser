package mouse.hoi.main.gfx.service.synch;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.gfx.data.SpriteType;
import mouse.hoi.tools.files.PathManager;
import mouse.hoi.main.gfx.service.SpriteTypesSynchronizer;
import mouse.hoi.main.gfx.service.properties.GFXType;
import mouse.hoi.main.gfx.service.properties.GfxProperties;
import mouse.hoi.tools.utils.NotNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoalsSpriteTypesSynch implements Synchronizer{
    private final SpriteTypesSynchronizer spriteTypesSynchronizer;
    private final PathManager pathManager;
    @Override
    public GFXType forType() {
        return GFXType.GOAL;
    }

    @Override
    public void sync(GfxProperties gfxProperties) {
        String file = gfxProperties.getGoalFile();
        String directory = gfxProperties.getGoalDirectory();
        NotNull.orThrow(file);
        NotNull.orThrow(directory);
        spriteTypesSynchronizer.synchronizeAndWrite(file, directory, this::factory);
    }
    private SpriteType factory(String file) {
        SpriteType spriteType = new SpriteType();
        String name = pathManager.build(file).name().noExtension().get();
        name = "GFX_" + name;
        spriteType.setName(name);
        String pathShort = pathManager.build(file).dir("gfx").get();
        spriteType.setTextureFile(pathShort);
        spriteType.setLegacyLazyLoad(true);
        return spriteType;
    }
}
