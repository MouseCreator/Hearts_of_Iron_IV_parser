package mouse.hoi.tools.parser.generator;

import mouse.hoi.exception.ScanException;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class YyCall {
    public YyResult call(String filename) {
        try {
            File file = new File(filename);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            List<Yytoken> yytokenList = read(bufferedReader);
            bufferedReader.close();
            return new YyResult(filename, yytokenList);

        } catch (Exception e) {
            throw new ScanException("Error reading \"" + filename + "\"", e);
        }
    }
    public YyResult read(String content) {
        try {
            StringReader stringReader = new StringReader(content);
            List<Yytoken> yytokenList = read(stringReader);
            return new YyResult("", yytokenList);
        } catch (Exception e) {
            throw new ScanException(e);
        }
    }

    private static List<Yytoken> read(Reader reader) throws IOException {
        Yylex yylex = new Yylex(reader);
        List<Yytoken> yytokenList = new ArrayList<>();
        while (!yylex.yyatEOF()) {
            Yytoken token = yylex.yylex();
            if (token != null) {
                yytokenList.add(token);
            }
        }
        reader.close();
        return yytokenList;
    }
}
