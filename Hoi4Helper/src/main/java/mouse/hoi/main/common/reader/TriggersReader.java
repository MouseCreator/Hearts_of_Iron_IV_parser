package mouse.hoi.main.common.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.common.data.trigger.FlowTrigger;
import mouse.hoi.main.common.data.trigger.Triggers;
import mouse.hoi.main.common.data.trigger.conditional.ConditionalTrigger;
import mouse.hoi.main.common.data.trigger.conditional.ConditionalTriggers;
import mouse.hoi.main.common.tester.TokenTester;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import mouse.hoi.tools.parser.impl.reader.helper.Readers;
import mouse.hoi.tools.parser.impl.reader.lr.LeftValue;
import mouse.hoi.tools.parser.impl.reader.lr.RightValue;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TriggersReader implements DataReader<Triggers> {

    private final Readers readers;
    private final TokenTester tester;
    @Override
    public Class<Triggers> forType() {
        return Triggers.class;
    }

    @Override
    public void onKeyValue(Triggers triggers, LeftValue leftValue, RightValue rightValue) {
        readers.lrValues().with(leftValue, rightValue)
                .onToken("if")
                .init(()->new ConditionalTrigger(triggers.getScope()))
                .onBlock().res().consume(c -> {
                    ConditionalTriggers ct = new ConditionalTriggers(triggers.getScope());
                    ct.setIfEffect(c);
                    triggers.getConditionalTriggers().add(ct);
                })
                .onToken("else_if")
                .init(()->new ConditionalTrigger(triggers.getScope()))
                .onBlock().res().consume(c -> {
                    ConditionalTriggers last = triggers.getConditionalTriggers().getLast();
                    last.addElseIf(c);
                })
                .onToken("else")
                .init(()->new Triggers(triggers.getScope()))
                .onBlock().res().consume(c -> {
                    ConditionalTriggers last = triggers.getConditionalTriggers().getLast();
                    last.setElseEffect(c);
                })
                .onToken("and").init(()->new FlowTrigger("and", triggers.getScope())).onBlock().res().push(triggers::getFlowTriggers)
                .onToken("or").init(()->new FlowTrigger("or", triggers.getScope())).onBlock().res().push(triggers::getFlowTriggers)
                .rememberInteger().map(triggers.getScope()::onInteger).map(Triggers::new)
                    .onBlock().res().consume(triggers::addSubTriggers)
                .rememberString().test(tester::isCountryTag).map(triggers.getScope()::onTag).map(Triggers::new)
                    .onBlock().res().consume(triggers::addSubTriggers)
                .orElseThrow();

    }
}
