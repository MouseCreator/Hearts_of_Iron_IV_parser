package mouse.hoi.launch;

import mouse.hoi.main.map.provinces.ProvinceSearchService;
import mouse.hoi.tools.context.Context;
import org.springframework.context.ApplicationContext;

public class ProvincesMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = Context.get();
        ProvinceSearchService service = applicationContext.getBean(ProvinceSearchService.class);
        service.search();
    }
}
