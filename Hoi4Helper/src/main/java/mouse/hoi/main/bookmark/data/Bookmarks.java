package mouse.hoi.main.bookmark.data;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class Bookmarks {
    private List<Bookmark> list;

    public Bookmarks() {
        list = new ArrayList<>();
    }
}
