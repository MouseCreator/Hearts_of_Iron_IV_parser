package mouse.hoi.main.io.reader;

import mouse.hoi.main.gfx.data.SpriteTypesWrapper;
import mouse.hoi.tools.context.Context;
import mouse.hoi.tools.parser.impl.ast.AbstractSyntaxTree;
import mouse.hoi.tools.parser.impl.bind.ScanParseBinder;
import mouse.hoi.tools.parser.impl.reader.engine.ReaderEngine;
import mouse.hoi.tools.parser.impl.writer.engine.WriterEngine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class SpriteTypesTest {
    private ReaderEngine reader;
    private WriterEngine writer;

    private ScanParseBinder binder;
    @BeforeEach
    void setUp() {
        reader = Context.get().getBean(ReaderEngine.class);
        writer = Context.get().getBean(WriterEngine.class);
        binder = Context.get().getBean(ScanParseBinder.class);
    }

    @Test
    void readWriteSimple() {

        String expected = """
                spriteTypes = {
                	SpriteType = {
                		name = "GFX_focus_ent_leyla_athella_shine"
                		texturefile = "gfx/interface/goals/focus_ent_leyla_athella.dds"
                		effectFile = "gfx/FX/buttonstate.lua"
                		animation = {
                			animationmaskfile = "gfx/interface/goals/focus_ent_leyla_athella.dds"
                			animationtexturefile = "gfx/interface/goals/shine_overlay.dds"
                			animationrotation = -90.000
                			animationlooping = no
                			animationtime = 0.750
                			animationdelay = 0.000
                			animationblendmode = "add"
                			animationtype = "scrolling"
                			animationrotationoffset = { x = 0.0 y = 0.0 }
                			animationtexturescale = { x = 1.0 y = 1.0 }
                		}
                		animation = {
                			animationmaskfile = "gfx/interface/goals/focus_ent_leyla_athella.dds"
                			animationtexturefile = "gfx/interface/goals/shine_overlay.dds"
                			animationrotation = 90.000
                			animationlooping = no
                			animationtime = 0.750
                			animationdelay = 0.000
                			animationblendmode = "add"
                			animationtype = "scrolling"
                			animationrotationoffset = { x = 0.0 y = 0.0 }
                			animationtexturescale = { x = 1.0 y = 1.0 }
                		}
                		legacy_lazy_load = no
                	}
                }
                
                """;
        AbstractSyntaxTree ast = binder.createTreeFromContent(expected);
        SpriteTypesWrapper fromContent = reader.read(ast, SpriteTypesWrapper.class);
        String backToString = writer.write(fromContent);
        Assertions.assertEquals(expected, backToString);
    }
}