package mouse.hoi.launch;

import mouse.hoi.main.bookmark.service.JustLoad;
import mouse.hoi.tools.context.App;

public class Main {
    public static void main(String[] args) {
        App.useService(JustLoad.class);
    }
}
