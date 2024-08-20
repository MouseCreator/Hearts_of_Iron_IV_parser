package mouse.hoi.main.common.data.effect;


import mouse.hoi.tools.parser.impl.dom.active.ActiveObject;
import mouse.hoi.tools.parser.impl.dom.active.ActiveWriter;

public abstract class SpecialEffect extends AbstractEffect {

    public SpecialEffect() {
    }
    @Override
    public boolean isSpecial() {
        return true;
    }
    public abstract void read(ActiveObject activeObject);
    public abstract ActiveWriter write();
}
