package mouse.hoi.launch;

import mouse.hoi.main.bookmark.service.BookmarksJustLoad;
import mouse.hoi.tools.context.App;

public class Main {
    public static void main(String[] args) {
        App.useService(BookmarksJustLoad.class);
    }
}
