package mouse.hoi.main.map.adjust;

import lombok.RequiredArgsConstructor;
import mouse.hoi.tools.context.AppService;
import mouse.hoi.tools.files.FileManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdjustPrinter implements AppService {
    private final FileManager fileManager;

    private record AdjRule(String from, String to, String through, String name) {
    }
    private static class AdjBuilder {
        private final StringBuilder builder;

        public AdjBuilder() {
            builder = new StringBuilder();
        }
        public AdjBuilder append(String str) {
            builder.append(str);
            return this;
        }
        public AdjBuilder cv() {
            builder.append(";");
            return this;
        }
        public AdjBuilder ln() {
            builder.append("\n");
            return this;
        }
        public String get() {
            return builder.toString();
        }
    }
    @Override
    public void start() {
        List<String> content = fileManager.readLines("src/main/resources/provinces/adjust.input");
        AdjBuilder builder = new AdjBuilder();
        String first = "From;To;Type;Through;start_x;start_y;stop_x;stop_y;adjacency_rule_name;Comment";
        String last = "-1;-1;;-1;-1;-1;-1;-1;-1 ";
        builder.append(first).ln();
        for (String line : content) {
            List<AdjRule> rules = toRule(line);
            for (AdjRule rule : rules) {
                builder.append(rule.from()).cv()
                        .append(rule.to()).cv()
                        .append("sea").cv()
                        .append(rule.through()).cv()
                        .append("-1;-1;-1;-1;;").append(rule.name()).ln();
            }
        }
        builder.append(last);
        System.out.println(builder.get());
    }

    private List<AdjRule> toRule(String line) {
        String[] split = line.split("-");
        String[] provinces = split[0].trim().split(" +");
        String[] extras = split[1].trim().split(" +", 2);
        String through = extras[0];
        String name = extras[1];
        List<AdjRule> result = new ArrayList<>();
        for (int i = 0; i < provinces.length; i++) {
            for (int j = i + 1;j < provinces.length; j++) {
                AdjRule adjRule = new AdjRule(provinces[i], provinces[j], through, name);
                result.add(adjRule);
            }
        }
        return result;
    }
}
