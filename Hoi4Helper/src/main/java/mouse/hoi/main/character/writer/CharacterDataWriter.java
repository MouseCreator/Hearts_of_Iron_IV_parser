package mouse.hoi.main.character.writer;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.character.data.CharacterData;
import mouse.hoi.main.character.data.CharacterRole;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.parser.impl.writer.dw.DWData;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;
import mouse.hoi.tools.parser.impl.writer.support.DWObjectBuilder;
import mouse.hoi.tools.parser.impl.writer.support.WriterSupport;
import mouse.hoi.tools.utils.NotNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterDataWriter implements DataWriter<CharacterData> {

    private final WriterSupport writerSupport;
    @Override
    public Class<CharacterData> forType() {
        return CharacterData.class;
    }

    @Override
    public DWData write(CharacterData object, ObjectStyle style) {
        DWObjectBuilder build = writerSupport.build(style);

        NotNull.supply(object::getName, s -> build.key("name").string(s));
        build.key("portraits").object(object::getPortraits);

        for (CharacterRole role : object.getCharacterRoleList()) {
            build.key(role.roleName()).objectRaw(role);
        }

        return build.get();
    }
}
