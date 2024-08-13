package mouse.hoi.launch;

import mouse.hoi.main.map.adjust.AdjustPrinter;
import mouse.hoi.tools.context.App;

public class AdjustMain {
    public static void main(String[] args) {
        App.useService(AdjustPrinter.class);
    }
}
