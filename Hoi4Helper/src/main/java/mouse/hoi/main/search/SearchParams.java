package mouse.hoi.main.search;

import lombok.Data;
import mouse.hoi.tools.properties.annotation.DefaultsTo;
import mouse.hoi.tools.properties.annotation.FromProperty;

@Data
public class SearchParams {
    @FromProperty
    private String target;
    @FromProperty
    private String directory;
    @FromProperty
    @DefaultsTo("false")
    private boolean caseSensitive;
    @FromProperty("fileNames")
    @DefaultsTo
    private boolean includeFilenames = false;
    @FromProperty
    @DefaultsTo("true")
    private boolean recursive;
    @FromProperty
    @DefaultsTo("false")
    private boolean printLine;
    @FromProperty
    @DefaultsTo
    private String extension = "";
}
