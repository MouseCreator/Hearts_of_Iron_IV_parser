package mouse.hoi.main.character.data;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Portraits {
    private List<PortraitType> portraitTypeList;

    public Portraits() {
        portraitTypeList = new ArrayList<>();
    }
}
