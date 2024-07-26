package mouse.hoi.tools.parser.impl.writer;

public interface StyledDataWriter<T, Y> extends DataWriter<T> {
    Y forStyle();
}
