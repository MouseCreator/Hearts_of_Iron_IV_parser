package mouse.hoi.main.bookmark.data;

import lombok.Data;
import mouse.hoi.tools.parser.impl.reader.Inits;

import java.util.ArrayList;
import java.util.List;
@Data
public class Bookmarks implements Inits {
    private List<Bookmark> list;

    @Override
    public void initialize() {
        list = new ArrayList<>();
    }
}
