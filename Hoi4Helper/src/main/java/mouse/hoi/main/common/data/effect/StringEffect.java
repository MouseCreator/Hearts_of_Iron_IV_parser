package mouse.hoi.main.common.data.effect;

public class StringEffect implements Effect{
    private String effectName;
    private String value;

    public StringEffect(String name, String value) {
        this.effectName = name;
        this.value = value;
    }

    @Override
    public String stringValue() {
        return value;
    }

    @Override
    public String getEffectName() {
        return effectName;
    }
}
