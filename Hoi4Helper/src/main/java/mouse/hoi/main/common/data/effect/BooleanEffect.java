package mouse.hoi.main.common.data.effect;

public abstract class BooleanEffect extends AbstractEffect{
    private boolean value;

    public void setValue(boolean v) {
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

}
