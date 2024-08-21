package mouse.hoi.main.gfx.service.synch;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.gfx.data.SpriteType;
import mouse.hoi.tools.files.PathManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommonFactories {

    private final PathManager pathManager;
    public SpriteType GFXPrefixedFactory(String file) {
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
