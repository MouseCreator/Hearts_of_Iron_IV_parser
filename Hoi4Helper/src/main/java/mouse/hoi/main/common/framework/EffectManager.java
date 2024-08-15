package mouse.hoi.main.common.framework;

import lombok.RequiredArgsConstructor;
import mouse.hoi.exception.EffectException;
import mouse.hoi.main.common.data.effect.*;
import mouse.hoi.main.common.data.effect.store.EffectData;
import mouse.hoi.main.common.data.scope.Scope;
import mouse.hoi.main.common.data.scope.ScopeEnum;
import mouse.hoi.tools.parser.impl.reader.lr.RightValue;
import mouse.hoi.tools.parser.impl.writer.SpecialWriter;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EffectManager {
    private final EffectHolderInitializer effectHolderInitializer;
    private final EffectReaderHelper effectReaderHelper;
    private final EffectFactory effectFactory;
    private final EffectDataStringify effectDataStringify;

    public Effect createEffect(String key, Scope scope, RightValue rightValue) {
        EffectsHolder effectHolder = effectHolderInitializer.effectsHolder();
        ScopeEnum scopeEnum = scope.enumValue();
        Optional<Class<?>> effectClass = effectHolder.onEffect(key, scopeEnum);
        if (effectClass.isEmpty()) {
            throw new EffectException(String.format("No effect has key '%s' and scope '%s'", key, scopeEnum));
        }
        Effect effect = effectFactory.createEffect(effectClass.get());
        defineEffect(key, effect, rightValue);
        return effect;
    }

    private void defineEffect(String key, Effect effect, RightValue rightValue) {
        if (effect.isSpecial()) {
            SpecialEffect specialEffect = (SpecialEffect) effect;
            EffectData effectData = effectReaderHelper.read(rightValue);
            specialEffect.read(effectData);
            return;
        }
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

    public void writeEffect(SpecialWriter writer, Effect effect) {
        if(effect.isSpecial()) {
            SpecialEffect specialEffect = (SpecialEffect) effect;
            EffectData data = specialEffect.write();
            effectDataStringify.write(data, writer);
        } else {
            String stringValue = effect.stringValue();
            writer.write(effect.key()).eq().write(stringValue);
        }
    }
}
