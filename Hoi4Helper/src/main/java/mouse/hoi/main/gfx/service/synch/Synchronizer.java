package mouse.hoi.main.gfx.service.synch;

import mouse.hoi.main.gfx.service.properties.GFXType;
import mouse.hoi.main.gfx.service.properties.GfxProperties;

public interface Synchronizer {
    GFXType forType();
    void sync(GfxProperties gfxProperties);
}
