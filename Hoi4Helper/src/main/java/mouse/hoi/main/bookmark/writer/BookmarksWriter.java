package mouse.hoi.main.bookmark.writer;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.bookmark.data.Bookmarks;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.parser.impl.writer.dw.DWData;
import mouse.hoi.tools.parser.impl.writer.dw.DWObject;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;
import mouse.hoi.tools.parser.impl.writer.support.DWObjectBuilder;
import mouse.hoi.tools.parser.impl.writer.support.WriterSupport;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookmarksWriter implements DataWriter<Bookmarks> {

    private final WriterSupport writerSupport;
    @Override
    public Class<Bookmarks> forType() {
        return Bookmarks.class;
    }

    @Override
    public DWData write(Bookmarks object, ObjectStyle style) {
        DWObjectBuilder b = writerSupport.build(style);
        b.listAll("bookmark").objects(object::getList);
        return b.get();
    }
}
