package mouse.hoi.main.common.data.effect;

public abstract class DoubleEffect implements Effect{
    private double value;

    public void setValue(double d) {
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
}
