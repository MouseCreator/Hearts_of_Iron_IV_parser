package mouse.hoi.main.character.data;

import lombok.Data;

import java.util.List;

@Data
public class CharacterData {
    private String id;
    private Portraits portraits;
    private List<CharacterRole> characterRoleList;
}
