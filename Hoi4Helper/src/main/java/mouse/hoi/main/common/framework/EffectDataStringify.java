package mouse.hoi.main.common.framework;

import mouse.hoi.exception.EffectException;
import mouse.hoi.main.common.data.effect.store.EffectData;
import mouse.hoi.main.common.data.effect.store.EffectDataList;
import mouse.hoi.main.common.data.effect.store.EffectDataObj;
import mouse.hoi.main.common.data.effect.store.EffectDataSimple;
import mouse.hoi.tools.parser.impl.writer.SpecialWriter;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EffectDataStringify {
    public void write(EffectData effectData, SpecialWriter specialWriter) {
        if (effectData.isSimple()) {
            writeSimple(effectData.simple(), specialWriter);
        } else if (effectData.isList()) {
            writeList(effectData.list(), specialWriter);
        } else if (effectData.isObject()) {
            writeObject(effectData.object(), specialWriter);
        } else {
            throw new EffectException("Unexpected effect data type: " + effectData);
        }
    }

    private void writeObject(EffectDataObj object, SpecialWriter specialWriter) {
        Set<String> keys = object.keys();
        for (String key : keys) {
            specialWriter.write(key).eq();
            Object any = object.getAny(key);
            if (any instanceof EffectData d) {
                write(d, specialWriter);
            } else {
                String str = any.toString();
                specialWriter.write(str);
            }
        }
    }

    private void writeList(EffectDataList list, SpecialWriter specialWriter) {
        specialWriter.list().simple(list.objects());
    }

    private void writeSimple(EffectDataSimple simple, SpecialWriter specialWriter) {
        Object any = simple.any();
        String string = any.toString();
        specialWriter.write(string);
    }
}
