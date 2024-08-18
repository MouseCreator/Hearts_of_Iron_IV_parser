package mouse.hoi.tools.parser.impl.writer.n.dw;

import mouse.hoi.tools.parser.data.GameDate;
import mouse.hoi.tools.parser.impl.writer.SpecialWriter;

public class DWDate implements DWSimple{
    private final GameDate gameDate;

    public DWDate(GameDate gameDate) {
        this.gameDate = gameDate;
    }
    @Override
    public void write(SpecialWriter writer) {
        String writtenDate = gameDate.write();
        writer.write(writtenDate);
    }
}
