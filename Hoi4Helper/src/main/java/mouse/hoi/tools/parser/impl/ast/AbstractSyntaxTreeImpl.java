package mouse.hoi.tools.parser.impl.ast;

public class AbstractSyntaxTreeImpl implements AbstractSyntaxTree {
    private final RootNode rootNode;

    public AbstractSyntaxTreeImpl(RootNode rootNode) {
        this.rootNode = rootNode;
    }

    @Override
    public RootNode root() {
        return rootNode;
    }
}
