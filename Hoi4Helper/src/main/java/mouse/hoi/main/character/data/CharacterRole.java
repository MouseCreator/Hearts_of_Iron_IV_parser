package mouse.hoi.main.character.data;

public interface CharacterRole {
    String roleName();
    default boolean requiresSmallPortrait() {
        return false;
    }
}
