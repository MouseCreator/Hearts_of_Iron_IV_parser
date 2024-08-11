package mouse.hoi.tools.files;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PathsLoader {
    public List<String> allFiles(String fromDirectory, boolean recursive, boolean absolutePath) {
        List<String> fileNames = new ArrayList<>();
        File dir = new File(fromDirectory);

        if (!dir.exists() || !dir.isDirectory()) {
            return fileNames;
        }

        listFiles(dir, fileNames, recursive, absolutePath, fromDirectory.length());
        fileNames = unifyFormat(fileNames);
        return fileNames;
    }

    public List<String> unifyFormat(List<String> fileNames) {
        return fileNames.stream()
                .map(this::unifyFormat)
                .toList();
    }

    private void listFiles(File dir, List<String> fileNames, boolean recursive, boolean absolutePath, int basePathLength) {
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.isDirectory() && recursive) {
                listFiles(file, fileNames, true, absolutePath, basePathLength);
            } else if (file.isFile()) {
                if (absolutePath) {
                    fileNames.add(file.getAbsolutePath());
                } else {
                    fileNames.add(file.getAbsolutePath().substring(basePathLength + 1).replace(File.separatorChar, '/'));
                }
            }
        }
    }

    public String unifyFormat(String fileName) {
        return fileName.replace("\\", "/");
    }
}
