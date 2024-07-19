package mouse.hoi.main.search;

import lombok.RequiredArgsConstructor;
import mouse.hoi.exception.SearchException;
import mouse.hoi.tools.files.FileManager;
import mouse.hoi.tools.utils.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class InFilesSearchImpl implements InFilesSearch {

    private final FileManager fileManager;

    private final Set<String> types = new HashSet<>();
    @Override
    public List<SearchOccurrence> find(SearchParams searchParams) {
        List<SearchOccurrence> occurrences = new ArrayList<>();
        File root = new File(searchParams.getDirectory());

        if (!root.exists()) {
            throw new SearchException("Search root does not exists");
        }

        if (root.isFile()) {
            searchInFile(root, searchParams, occurrences);
        } else if (root.isDirectory()) {
            searchInDirectory(root, searchParams, occurrences);
        }
        return occurrences;
    }
    private void searchInDirectory(File directory, SearchParams searchParams, List<SearchOccurrence> occurrences) {
        File[] files = directory.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory() && searchParams.isRecursive()) {
                searchInDirectory(file, searchParams, occurrences);
            } else if (file.isFile()) {
                searchInFile(file, searchParams, occurrences);
            }
        }
    }
    private void searchInFile(File file, SearchParams searchParams, List<SearchOccurrence> occurrences) {
        if (!isTextFile(file, searchParams)) {
            return;
        }
        List<String> lines = fileManager.readLines(file.getAbsolutePath());

        int row = 0;
        String target = searchParams.getTarget();
        if (!searchParams.isCaseSensitive()) {
            target = target.toLowerCase();
        }

        for (String line : lines) {
            row++;
            String searchLine = searchParams.isCaseSensitive() ? line : line.toLowerCase();

            int position = searchLine.indexOf(target);
            while (position >= 0) {
                int pPos = position + 1;
                if (searchParams.isPrintLine()) {
                    occurrences.add(new LineContentOccurrence(file.getAbsolutePath(), line, row, pPos));
                } else {
                    occurrences.add(new FileContentOccurrence(file.getAbsolutePath(), row, pPos));
                }
                position = searchLine.indexOf(target, position + 1);
            }
        }

        if (searchParams.isIncludeFilenames() && !occurrences.isEmpty()) {
            occurrences.add(new FilenameOccurrence(file.getAbsolutePath()));
        }

    }
    private boolean isTextFile(File file, SearchParams searchParams) {
        String extension = searchParams.getExtension();
        if (!extension.isEmpty()) {
            return extension.equals(FileUtils.extension(file));
        }
        Path path = file.toPath();
        try {
            String mimeType = Files.probeContentType(path);
            types.add(mimeType);
            if (mimeType == null) {
                return true;
            }
            return mimeType.startsWith("text");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
