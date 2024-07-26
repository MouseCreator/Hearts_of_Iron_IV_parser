package mouse.hoi.main.gfx.data;

import lombok.Data;

import java.util.List;

@Data
public class AbstractWrapper<T> implements DataWrapper<T> {
    private List<T> list;
}
