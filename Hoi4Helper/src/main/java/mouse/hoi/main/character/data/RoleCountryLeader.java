package mouse.hoi.main.character.data;

import lombok.Data;

@Data
public class RoleCountryLeader implements CharacterRole{

    @Override
    public String roleName() {
        return "country_leader";
    }
    private String ideology;
    private String expire;
    private int id;
}
