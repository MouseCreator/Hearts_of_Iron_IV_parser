package mouse.hoi.tools.parser.impl.writer;

import mouse.hoi.tools.parser.impl.writer.dw.DWData;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
@Service
public class DataWriterManager {
    private final Map<Class<?>, DataWriter<?>> writerMap;
    public DataWriterManager(List<DataWriter<?>> writerList) {
        writerMap = new HashMap<>();
        for (DataWriter<?> dataWriter : writerList) {
            writerMap.put(dataWriter.forType(), dataWriter);
        }
    }

    public DWData write(Object object) {
        DataWriter<?> dataWriter = writerMap.get(object.getClass());
        return useWriter(dataWriter, object, ObjectStyle.DEFAULT);
    }
    public DWData write(Object object, ObjectStyle style) {
        DataWriter<?> dataWriter = writerMap.get(object.getClass());
        return useWriter(dataWriter, object, style);
    }

    private <T> DWData useWriter(DataWriter<T> dataWriter, Object object, ObjectStyle style) {
        T cast = dataWriter.forType().cast(object);
        return dataWriter.write(cast, style);
    }

}
