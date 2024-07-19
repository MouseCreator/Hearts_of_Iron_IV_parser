package mouse.hoi.tools.utils;

import java.io.File;

public class FileUtils {
    public static String extension(File file) {
        return extension(file.getAbsolutePath());
    }
    public static String extension(String str) {
        int dot = str.lastIndexOf(".");
        int slash = str.lastIndexOf("/");
        if (dot < slash) {
            return "";
        } else {
            return str.substring(dot+1);
        }
    }
}
