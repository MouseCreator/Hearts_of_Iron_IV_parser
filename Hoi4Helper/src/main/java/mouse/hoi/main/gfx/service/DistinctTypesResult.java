package mouse.hoi.main.gfx.service;

import lombok.Data;
import mouse.hoi.main.gfx.data.SpriteType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Data
public class DistinctTypesResult {
    private List<String> missing;
    private List<SpriteType> noLongerInUse;

    public DistinctTypesResult(Collection<String> missing, Collection<SpriteType> noLongerInUse) {
        this.missing = new ArrayList<>(missing);
        this.noLongerInUse = new ArrayList<>(noLongerInUse);
    }
}
