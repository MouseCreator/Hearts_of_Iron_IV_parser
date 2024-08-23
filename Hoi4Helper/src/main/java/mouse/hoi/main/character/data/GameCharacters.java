package mouse.hoi.main.character.data;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GameCharacters {
    private List<CharacterData> characterList;

    public GameCharacters() {
        characterList = new ArrayList<>();
    }
}
