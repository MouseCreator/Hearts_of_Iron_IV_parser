package mouse.hoi.main.gfx.service.synch;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.gfx.service.SpriteTypesSynchronizer;
import mouse.hoi.main.gfx.service.properties.GFXType;
import mouse.hoi.main.gfx.service.properties.GfxProperties;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PortraitsTypesSynch implements Synchronizer{

    private final SpriteTypesSynchronizer spriteTypesSynchronizer;
    private final CommonFactories commonFactories;
    @Override
    public GFXType forType() {
        return GFXType.CHARACTER;
    }

    @Override
    public void sync(GfxProperties gfxProperties) {
        String charactersDirectory = gfxProperties.getCharactersDirectory();
        String charactersFile = gfxProperties.getCharactersFile();
        spriteTypesSynchronizer.synchronizeAndWrite(charactersFile, charactersDirectory, commonFactories::GFXPrefixedFactory);
    }
}
