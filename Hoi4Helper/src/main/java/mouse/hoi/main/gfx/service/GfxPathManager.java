package mouse.hoi.main.gfx.service;

import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class GfxPathManager {
    public String getFileNameWithoutExtension(String filePath) {
        String fileName = getFileNameWithExtension(filePath);
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0) {
            return fileName.substring(0, dotIndex);
        }
        return fileName;
    }

    public String getPathFromLastDirectory(String filePath, String directoryName) {
        String[] parts = filePath.split("/");
        int lastIndex = -1;
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].equals(directoryName)) {
                lastIndex = i;
            }
        }
        if (lastIndex == -1) {
            throw new RuntimeException("Cannot find directory " + directoryName + " in path: " + filePath);
        }
        StringBuilder result = new StringBuilder();
        for (int i = lastIndex; i < parts.length; i++) {
            if (i > lastIndex) {
                result.append("/");
            }
            result.append(parts[i]);
        }
        return result.toString();
    }

    public String getFileNameWithExtension(String filePath) {
        String[] parts = filePath.split("/");
        return parts[parts.length - 1];
    }


    public PathBuilder build(String base) {
        return new PathBuilder(this, base);
    }

    public static class PathBuilder {
        private final GfxPathManager parent;
        private String myPath;

        public PathBuilder(GfxPathManager parent, String myPath) {
            this.parent = parent;
            this.myPath = myPath;
        }
        public String get() {
            return myPath;
        }
        public PathBuilder dir(String dir) {
            myPath = parent.getPathFromLastDirectory(myPath, dir);
            return this;
        }
        public PathBuilder noExtension() {
            myPath = parent.getFileNameWithoutExtension(myPath);
            return this;
        }

        public PathBuilder name() {
            myPath = parent.getFileNameWithExtension(myPath);
            return this;
        }
    }
}
