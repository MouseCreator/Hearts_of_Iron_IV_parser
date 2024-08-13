package mouse.hoi.launch;

import mouse.hoi.main.states.service.categories.StateCategoryService;
import mouse.hoi.tools.context.App;

public class CategoryMain {
    public static void main(String[] args) {
        App.useService(StateCategoryService.class);
    }
}
