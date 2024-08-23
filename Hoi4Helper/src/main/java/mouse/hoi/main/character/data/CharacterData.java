package mouse.hoi.main.character.data;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CharacterData {
    private String id;
    private String name;
    private Portraits portraits;
    private List<CharacterRole> characterRoleList;

    public CharacterData() {
        portraits = new Portraits();
        characterRoleList = new ArrayList<>();
    }

    public CharacterData(String key) {
        this.id = key;
        portraits = new Portraits();
        characterRoleList = new ArrayList<>();
    }
    public String possibleName() {
        return name == null ? id : name;
    }
}
