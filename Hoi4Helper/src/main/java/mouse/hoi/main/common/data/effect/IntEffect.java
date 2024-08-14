package mouse.hoi.main.common.data.effect;


public class IntEffect implements Effect {
    private final String effectName;
    private final int value;

    public IntEffect(String name, int value) {
        this.effectName = name;
        this.value = value;
    }

    @Override
    public String getEffectName() {
        return effectName;
    }

    @Override
    public boolean isInteger() {
        return true;
    }

    @Override
    public int intValue() {
        return value;
    }

    @Override
    public boolean isDouble() {
        return true;
    }

    @Override
    public double doubleValue() {
        return value;
    }
}
