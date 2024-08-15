package mouse.hoi.main.common.data.effect;


import mouse.hoi.main.common.data.effect.store.EffectData;

public abstract class SpecialEffect implements Effect{

    public SpecialEffect() {

    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    public abstract void read(EffectData effectDataInput);
    public abstract EffectData write();
}
