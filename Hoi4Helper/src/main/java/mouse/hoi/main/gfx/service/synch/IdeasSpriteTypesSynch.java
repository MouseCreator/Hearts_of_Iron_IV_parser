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
public class IdeasSpriteTypesSynch implements Synchronizer {

    private final SpriteTypesSynchronizer spriteTypesSynchronizer;
    private final CommonFactories commonFactories;
    @Override
    public GFXType forType() {
        return GFXType.IDEA;
    }

    @Override
    public void sync(GfxProperties gfxProperties) {
        String file = gfxProperties.getIdeasFile();
        String directory = gfxProperties.getIdeasDirectory();
        NotNull.orThrow(file);
        NotNull.orThrow(directory);
        spriteTypesSynchronizer.synchronizeAndWrite(file, directory, commonFactories::GFXPrefixedFactory);
    }
}
