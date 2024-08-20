package mouse.hoi.main.common.framework.effect;

import lombok.RequiredArgsConstructor;
import mouse.hoi.exception.EffectException;
import mouse.hoi.main.common.data.effect.*;
import mouse.hoi.main.common.data.scope.Scope;
import mouse.hoi.main.common.data.scope.ScopeEnum;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.DomKV;
import mouse.hoi.tools.parser.impl.dom.active.ActiveObjectManager;
import mouse.hoi.tools.parser.impl.dom.active.ActiveWriter;
import mouse.hoi.tools.parser.impl.reader.lr.SimpleValue;
import mouse.hoi.tools.parser.impl.writer.dw.DWData;
import mouse.hoi.tools.parser.impl.writer.support.DWObjectBuilder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EffectManager {
    private final EffectHolderInitializer effectHolderInitializer;
    private final EffectFactory effectFactory;
    private final ActiveObjectManager activeObjectManager;

    public Effect createEffect(DomKV dom, Scope scope) {
        EffectsHolder effectHolder = effectHolderInitializer.effectsHolder();
        ScopeEnum scopeEnum = scope.enumValue();
        String string = dom.key().val().stringValue();
        Optional<Class<?>> effectClass = effectHolder.onEffect(string, scopeEnum);
        if (effectClass.isEmpty()) {
            throw new EffectException(String.format("No effect has key '%s' and scope '%s'", string, scopeEnum));
        }
        Effect effect = effectFactory.createEffect(effectClass.get());
        defineEffect(string, effect, dom.value());
        return effect;
    }

    private void defineEffect(String key, Effect effect, DomData domData) {
        if (effect.isSpecial()) {
            SpecialEffect specialEffect = (SpecialEffect) effect;
            specialEffect.read(activeObjectManager.createActiveObject(domData));
            return;
        }
        if (!domData.isSimple()) {
            throw new EffectException("Not special effect '" + key + "' defined with complex value: " + domData);
        }
        SimpleValue rightValue = domData.simple().val();
        if (effect.isInteger()) {
            if (rightValue.isInteger()) {
                IntEffect intEffect = (IntEffect) effect;
                intEffect.setValue(rightValue.intValue());
                return;
            } else {
                throw new EffectException("Effect " + key + " cannot be initialized with integer");
            }
        }
        if (effect.isDouble()) {
            if (rightValue.isDouble()) {
                DoubleEffect dEffect = (DoubleEffect) effect;
                dEffect.setValue(rightValue.doubleValue());
                return;
            } else {
                throw new EffectException("Effect " + key + " cannot be initialized with double");
            }
        }
        if (effect.isBoolean()) {
            if (rightValue.isBoolean()) {
                BooleanEffect bEffect = (BooleanEffect) effect;
                bEffect.setValue(rightValue.boolValue());
                return;
            } else {
                throw new EffectException("Effect " + key + " cannot be initialized with integer");
            }
        }
        if (effect.isString()) {
            String s = rightValue.stringValue();
            StringEffect stringEffect = (StringEffect) effect;
            stringEffect.setValue(s);
            return;
        }
        throw new EffectException("Effect " + key + " cannot be initialized with " + rightValue);
    }

    public void writeEffect(DWObjectBuilder b, Effect effect) {
        if(effect.isSpecial()) {
            SpecialEffect specialEffect = (SpecialEffect) effect;
            ActiveWriter active = specialEffect.write();
            b.key(effect.key()).value(active.getDWData());
        } else {
            DWData dwData = effect.dwValue();
            b.key(effect.key()).value(dwData);
        }
    }


}
