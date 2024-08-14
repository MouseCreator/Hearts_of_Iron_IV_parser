package mouse.hoi.main.common.data.effect;

public class DoubleEffect implements Effect{
    private String effectName;
    private double value;

    public DoubleEffect(String name, double d) {
        this.effectName = name;
        this.value = d;
    }

    @Override
    public boolean isDouble() {
        return true;
    }

    @Override
    public double doubleValue() {
        return value;
    }

    @Override
    public String getEffectName() {
        return effectName;
    }
}
