package ChineseConverter;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class FolderFileGetter {
    public static List<String> getFilePathList(String folderPath) {
        File folderFile = new File(folderPath);

        if (folderFile.isFile()) {
            return Collections.singletonList(folderPath);
        } else {
            List<String> filePathList = new ArrayList<>();
            for (File file : Objects.requireNonNull(folderFile.listFiles())) {
                if (file.isFile()) {
                    filePathList.add(file.getAbsolutePath());
                } else {
                    filePathList.addAll(getFilePathList(file.getAbsolutePath()));
                }
            }
            return filePathList;
        }
    }
}
