package mouse.hoi.main.gfx.service.properties;

import lombok.Data;
import mouse.hoi.tools.properties.annotation.FromProperty;

@Data
public class GfxProperties {
    @FromProperty
    private String active;
    @FromProperty("goals_file")
    private String goalFile;
    @FromProperty("goals_dir")
    private String goalDirectory;
    @FromProperty("goals_shine_file")
    private String goalShinesFile;
    @FromProperty("ideas_file")
    private String ideasFile;
    @FromProperty("ideas_dir")
    private String ideasDirectory;
    @FromProperty("leaders_dir")
    private String charactersDirectory;
    @FromProperty("leaders_file")
    private String charactersFile;

}
