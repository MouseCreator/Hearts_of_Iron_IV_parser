package mouse.hoi.tools.parser.generator;

%%
%public

%{
    private StringBuilder currentString;
%}

%line
%char
%column
%state COMMENT
%state STRING
%unicode

ALPHA=[A-Za-z]
DIGIT=[0-9]
SPACE=[\ \t\b\012]
NEWLINE=\r|\n|\r\n
IDENTIFIER = ({ALPHA}|_)({ALPHA}|{DIGIT}|:|_)*

%%

<YYINITIAL> {
  "=" { return new Yytoken(TokenType.SPECIAL, yytext(), yyline, yycolumn);  }
  "<" { return new Yytoken(TokenType.SPECIAL, yytext(), yyline, yycolumn);  }
  ">" { return new Yytoken(TokenType.SPECIAL, yytext(), yyline, yycolumn); }
  "{" { return new Yytoken(TokenType.SPECIAL, yytext(), yyline, yycolumn); }
  "}" { return new Yytoken(TokenType.SPECIAL, yytext(), yyline, yycolumn); }

  {SPACE}+ { }
  {NEWLINE} {
  }

  "#" { yybegin(COMMENT); }

  "\"" {
    currentString = new StringBuilder();
    yybegin(STRING);
  }

  {DIGIT}+ { return new Yytoken(TokenType.INT, yytext(), yyline, yycolumn); }
  {DIGIT}+.{DIGIT}+ { return new Yytoken(TokenType.DOUBLE, yytext(), yyline, yycolumn); }
  {IDENTIFIER} { return new Yytoken(TokenType.ID, yytext(), yyline, yycolumn); }
}

<COMMENT> {
  {NEWLINE} {
    yybegin(YYINITIAL);
  }
  . { }
}

<STRING> {
  {NEWLINE} {
    Errors.report("Unmatched end of string: newline", yyline, yycolumn);
  }
  %eofval {
    Errors.report("Unmatched end of string: end of file", yyline, yycolumn);
  }
  \" {
    yybegin(YYINITIAL);
    currentString.append("\"");
    return new Yytoken(TokenType.STRING, currentString.toString(), yyline, yycolumn);
  }
  . { currentString.append(yytext()); }
}
. {
	Errors.report("Unexpected symbol: <" + yytext() + ">", yyline, yycolumn);
}

