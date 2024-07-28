package mouse.hoi.tools.parser.impl.writer.engine;

import mouse.hoi.exception.WriterException;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.parser.impl.writer.SpecialWriter;
import mouse.hoi.tools.parser.impl.writer.StyledDataWriter;
import mouse.hoi.tools.parser.impl.writer.WriterHelper;
import mouse.hoi.tools.parser.impl.writer.annotation.DefaultWriter;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WriterEngine {

    private final Map<Class<?>, List<DataWriter<?>>> writersMap;
    private final WriterHelper writerHelper;
    public WriterEngine(List<DataWriter<?>> writers, WriterHelper writerHelper) {
        this.writersMap = new HashMap<>();
        for (DataWriter<?> writer : writers) {
            List<DataWriter<?>> dataWriters = writersMap.computeIfAbsent(writer.forType(), k -> new ArrayList<>());
            dataWriters.add(writer);
        }
        this.writerHelper = writerHelper;
        writerHelper.setEngine(this);
    }
    public String write(Object objectToWrite) {
        SpecialWriter specialWriter = new SpecialWriter(writerHelper);
        writeObj(specialWriter, objectToWrite);
        return specialWriter.get();
    }
    public String write(Object objectToWrite, Object style) {
        SpecialWriter specialWriter = new SpecialWriter(writerHelper);
        writeObj(specialWriter, objectToWrite, style);
        return specialWriter.get();
    }

    public void writeObj(SpecialWriter specialWriter, Object objectToWrite) {
        Class<?> typeOfObj = objectToWrite.getClass();
        DataWriter<?> dataWriter = pickDataWriter(typeOfObj);

        useWriter(dataWriter, specialWriter, objectToWrite);
    }
    public void writeObj(SpecialWriter specialWriter, Object objectToWrite, Object style) {
        Class<?> typeOfObj = objectToWrite.getClass();
        DataWriter<?> dataWriter = pickDataWriter(typeOfObj, style);

        useWriter(dataWriter, specialWriter, objectToWrite);
    }

    private DataWriter<?> pickDataWriter(Class<?> typeOfObj) {
        List<DataWriter<?>> dataWriters = writersMap.get(typeOfObj);
        if (dataWriters == null || dataWriters.isEmpty()) {
            throw new WriterException("No writer for class: " + typeOfObj);
        }
        if (dataWriters.size()==1) {
            return dataWriters.getFirst();
        }
        Optional<DataWriter<?>> optionalDefaultWriter = dataWriters.stream().filter(d -> d.getClass().isAnnotationPresent(DefaultWriter.class)).findAny();
        if (optionalDefaultWriter.isPresent()) {
            return optionalDefaultWriter.get();
        }
        throw new WriterException("Multiple writers defined for class " + typeOfObj + ": " + dataWriters);
    }

    private DataWriter<?> pickDataWriter(Class<?> typeOfObj, Object style) {
        List<DataWriter<?>> dataWriters = writersMap.get(typeOfObj);
        List<StyledDataWriter<?,?>> styledWriters = toStyled(dataWriters);
        List<StyledDataWriter<?, ?>> filteredStyled = styledWriters.stream().filter(s -> style.equals(s.forStyle())).toList();
        if (filteredStyled.isEmpty()) {
            throw new WriterException("No writer for class: " + typeOfObj);
        }
        if (filteredStyled.size()==1) {
            return filteredStyled.getFirst();
        }
        throw new WriterException("Multiple styled writers defined for class " + typeOfObj + ": " + dataWriters);
    }

    private List<StyledDataWriter<?,?>> toStyled(List<DataWriter<?>> dataWriters) {
        List<StyledDataWriter<?,?>> list = new ArrayList<>();
        for (DataWriter<?> dataWriter : dataWriters) {
            if (dataWriter instanceof StyledDataWriter<?,?> d) {
                list.add(d);
            }
        }
        return list;
    }

    private <T> void useWriter(DataWriter<T> dataWriter, SpecialWriter specialWriter, Object objectToWrite) {
        Class<T> type = dataWriter.forType();
        dataWriter.write(specialWriter, type.cast(objectToWrite));
    }
}
