package mouse.hoi.main.bookmark.data;

import lombok.Data;

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
