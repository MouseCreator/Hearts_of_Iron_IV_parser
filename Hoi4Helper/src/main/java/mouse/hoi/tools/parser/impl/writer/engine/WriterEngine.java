package mouse.hoi.tools.parser.impl.writer.engine;

import mouse.hoi.exception.WriterException;
import mouse.hoi.tools.parser.impl.writer.DWWriter;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.parser.impl.writer.dw.DWData;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WriterEngine {
    private final Map<Class<?>, List<DataWriter<?>>> writersMap;
    private final DWWriter dwWriter;
    public WriterEngine(List<DataWriter<?>> writers, DWWriter dwWriter) {
        this.dwWriter = dwWriter;
        this.writersMap = new HashMap<>();
        for (DataWriter<?> writer : writers) {
            List<DataWriter<?>> dataWriters = writersMap.computeIfAbsent(writer.forType(), k -> new ArrayList<>());
            dataWriters.add(writer);
        }
    }
    public String write(Object objectToWrite) {
        DWData dwData = writeObj(objectToWrite, ObjectStyle.DEFAULT);
        return dwWriter.write(dwData);
    }
    public String write(Object objectToWrite, ObjectStyle objectStyle) {
        DWData dwData = writeObj(objectToWrite, objectStyle);
        return dwWriter.write(dwData);
    }

    public DWData writeObj(Object object, ObjectStyle style) {
        Class<?> typeOfObj = object.getClass();
        DataWriter<?> dataWriter = pickDataWriter(typeOfObj);
        return useWriter(dataWriter, object, style);
    }

    private DataWriter<?> pickDataWriter(Class<?> typeOfObj) {
        List<DataWriter<?>> dataWriters = writersMap.get(typeOfObj);
        if (dataWriters == null || dataWriters.isEmpty()) {
            throw new WriterException("No writer for class: " + typeOfObj);
        }
        if (dataWriters.size()==1) {
            return dataWriters.getFirst();
        }
        throw new WriterException("Multiple writers defined for class " + typeOfObj + ": " + dataWriters);
    }

    private <T> DWData useWriter(DataWriter<T> dataWriter, Object objectToWrite, ObjectStyle objectStyle) {
        T cast = dataWriter.forType().cast(objectToWrite);
        return dataWriter.write(cast, objectStyle);
    }
}
