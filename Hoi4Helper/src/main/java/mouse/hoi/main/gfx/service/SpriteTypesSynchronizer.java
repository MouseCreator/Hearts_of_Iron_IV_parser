package mouse.hoi.main.gfx.service;

import mouse.hoi.main.gfx.data.SpriteType;
import mouse.hoi.main.gfx.data.SpriteTypes;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SpriteTypesSynchronizer {
    public SpriteTypes synchronizeSpriteTypes(Collection<String> filenames, SpriteTypes spriteTypes) {
        SpriteTypes result = new SpriteTypes();
        Set<String> originSet = new HashSet<>();
        List<SpriteType> spriteTypeList = spriteTypes.getSpriteTypeList();
        return result;
    }
}


