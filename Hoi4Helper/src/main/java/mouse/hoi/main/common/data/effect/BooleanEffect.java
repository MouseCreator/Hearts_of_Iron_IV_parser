package mouse.hoi.main.common.data.effect;

public class BooleanEffect implements Effect{
    private String name;
    private boolean value;

    public BooleanEffect(String name, boolean v) {
        this.name = name;
        this.value = v;
    }

    @Override
    public boolean isBoolean() {
        return true;
    }

    @Override
    public boolean booleanValue() {
        return value;
    }

    @Override
    public String getEffectName() {
        return name;
    }

}
