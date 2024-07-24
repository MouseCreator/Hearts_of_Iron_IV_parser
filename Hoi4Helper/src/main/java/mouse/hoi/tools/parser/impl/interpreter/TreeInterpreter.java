package mouse.hoi.tools.parser.impl.interpreter;
// FOR INTERPRETER:
// if field is primitive or string -- init with key=val
// if field is an object -- init with key= { val } (recursive)
// if field is an object and its type is defaultable, init with default key=value
// if field is an object and its type is initialized, call initialize() method

// use expect() to validate syntax;
// interpreter will be used in order to assign types and scopes (mio, var, country)
// probably, it is better to create the AST first (!)

// elements:
// K=V
// K = { block }

/*
    A class may implement different interfaces, which can help with parsing (for example, Defaultable);
    Also we can use annotations to assign some properties
 */

import mouse.hoi.tools.parser.impl.ast.AbstractSyntaxTree;

public interface TreeInterpreter {
    <T> T mapToObject(AbstractSyntaxTree tree, Class<T> clazz);
}
