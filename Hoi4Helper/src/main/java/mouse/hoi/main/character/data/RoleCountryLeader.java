package mouse.hoi.main.character.data;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RoleCountryLeader implements CharacterRole{

    @Override
    public String roleName() {
        return "country_leader";
    }
    private String ideology;
    private String expire;
    private int id;
    private List<String> traits;

    public RoleCountryLeader() {
        traits = new ArrayList<>();
        id = -1;
    }
}
