package mouse.hoi.tools.parser.generator;

public record Yytoken(TokenType type, String value, int line, int column) {
}
