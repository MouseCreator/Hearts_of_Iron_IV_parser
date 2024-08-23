package mouse.hoi.main.character.data;

import lombok.Data;

import java.util.List;

@Data
public class AiWillDo {
    private double factor;
    private List<AiWillDoModifier> modifierList;
}
