package mouse.hoi.main.character.data;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class CharacterWrapper {
    private List<GameCharacters> charactersList;

    public CharacterWrapper() {
        charactersList = new ArrayList<>();
    }
}
