package mouse.hoi.main.bookmark.data;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class BookmarksWrapper {
    private List<Bookmarks> bookmarksList;

    public BookmarksWrapper() {
        this.bookmarksList = new ArrayList<>();
    }
}
