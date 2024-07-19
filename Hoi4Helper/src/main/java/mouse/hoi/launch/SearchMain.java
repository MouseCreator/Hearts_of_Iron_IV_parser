package mouse.hoi.launch;

import mouse.hoi.main.search.SearchService;
import mouse.hoi.tools.context.Context;
import org.springframework.context.ApplicationContext;

public class SearchMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = Context.get();
        SearchService search = applicationContext.getBean(SearchService.class);
        search.search();
    }
}
