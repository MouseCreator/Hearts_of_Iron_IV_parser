package mouse.hoi.main.gfx.service;

import mouse.hoi.main.gfx.data.SpriteType;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class DistinctSpriteTypesManager {
    public DistinctTypesResult onDistinct(Collection<SpriteType> spriteTypes, Collection<String> filenames) {
        Collection<SpriteType> toDelete = findToDelete(filenames, spriteTypes);
        Collection<String> missing = findToAppend(filenames, spriteTypes);
        return new DistinctTypesResult(missing, toDelete);
    }

    private Collection<String> findToAppend(Collection<String> filenames, Collection<SpriteType> spriteTypeList) {
        Set<String> usedNames = new HashSet<>(filenames);
        for (SpriteType spriteType : spriteTypeList) {
            String textureFile = spriteType.getTextureFile();
            usedNames.remove(textureFile);
        }
        return usedNames;
    }

    private Collection<SpriteType> findToDelete(Collection<String> filenames, Collection<SpriteType> spriteTypeList) {
        Map<String, SpriteType> unusedNames = new HashMap<>();
        for (SpriteType spriteType : spriteTypeList) {
            unusedNames.put(spriteType.getTextureFile(), spriteType);
        }
        for (String filename : filenames) {
            unusedNames.remove(filename);
        }
        return unusedNames.values();
    }

}
