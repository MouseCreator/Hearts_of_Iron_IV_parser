package mouse.hoi.main.common.data.effect;

public abstract class SpecialEffect implements Effect{

    public SpecialEffect(String effectName) {
        this.effectName = effectName;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
    protected String effectName;
    @Override
    public String getEffectName() {
        return effectName;
    }
}
