package mouse.hoi.main.bookmark.writer;

import mouse.hoi.main.bookmark.data.Bookmarks;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.parser.impl.writer.SpecialWriter;
import org.springframework.stereotype.Service;

@Service
public class BookmarksWriter implements DataWriter<Bookmarks> {
    @Override
    public Class<Bookmarks> forType() {
        return Bookmarks.class;
    }

    @Override
    public void write(SpecialWriter writer, Bookmarks object) {
        writer.list().block("bookmark", object::getList);
    }
}
