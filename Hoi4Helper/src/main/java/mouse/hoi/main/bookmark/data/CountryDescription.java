package mouse.hoi.main.bookmark.data;

import lombok.Data;

import java.util.List;
@Data
public class CountryDescription {
    public CountryDescription(String tag) {
        this.tag = tag;
    }

    private String tag;
    private String history;
    private String ideology;
    private List<String> ideas;
    private List<String> focuses;
    private boolean minor;
}
