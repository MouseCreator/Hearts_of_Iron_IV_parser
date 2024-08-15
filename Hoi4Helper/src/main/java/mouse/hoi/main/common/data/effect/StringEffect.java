package mouse.hoi.main.common.data.effect;

public abstract class StringEffect implements Effect{

    protected String value;

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String stringValue() {
        return value;
    }

    @Override
    public boolean isString() {
        return true;
    }
}
