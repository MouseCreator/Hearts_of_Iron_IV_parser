package mouse.hoi.tools.parser.impl.compile;

import mouse.hoi.main.gfx.data.SpriteTypesWrapper;
import mouse.hoi.tools.context.Context;
import mouse.hoi.tools.parser.impl.writer.ObjectWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContentCompilerTest {


    private ContentCompiler contentCompiler;

    private ObjectWriter objectWriter;

    @BeforeEach
    void setUp() {
        contentCompiler = Context.get().getBean(ContentCompiler.class);
        objectWriter = Context.get().getBean(ObjectWriter.class);
    }

    @Test
    void compile() {
        String content = """
                spriteTypes = {
                               
                               
                               
                	SpriteType = {
                		name = "GFX__shine"
                		texturefile = "gfx/interface/goals/goal_unknown.dds"
                				effectFile = "gfx/FX/buttonstate.lua"
                		animation = {
                			animationmaskfile = "gfx/interface/goals/goal_unknown.dds"
                			animationtexturefile = "gfx/interface/goals/shine_overlay.dds" 	# <- the animated file
                			animationrotation = -90.0		# -90 clockwise 90 counterclockwise(by default)
                			animationlooping = no			# yes or no ;)
                			animationtime = 0.75				# in seconds
                			animationdelay = 0			# in seconds
                			animationblendmode = "add"       #add, multiply, overlay
                			animationtype = "scrolling"      #scrolling, rotating, pulsing
                			animationrotationoffset = { x = 0.0 y = 0.0 }
                			animationtexturescale = { x = 1.0 y = 1.0 }\s
                		}
                               
                		animation = {
                			animationmaskfile = "gfx/interface/goals/goal_unknown.dds"
                			animationtexturefile = "gfx/interface/goals/shine_overlay.dds" 	# <- the animated file
                			animationrotation = 90.0		# -90 clockwise 90 counterclockwise(by default)
                			animationlooping = no			# yes or no ;)
                			animationtime = 0.75				# in seconds
                			animationdelay = 0			# in seconds
                			animationblendmode = "add"       #add, multiply, overlay
                			animationtype = "scrolling"      #scrolling, rotating, pulsing
                			animationrotationoffset = { x = 0.0 y = 0.0 }
                			animationtexturescale = { x = 1.0 y = 1.0 }\s
                		}
                		legacy_lazy_load = no
                	}
                	#### Generic continuous goal icons
                }
                """;
        SpriteTypesWrapper compiled = contentCompiler.compile(content, SpriteTypesWrapper.class);
        String write = objectWriter.write(compiled);
        System.out.println(write);
    }
}