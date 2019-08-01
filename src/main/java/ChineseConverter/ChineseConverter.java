package ChineseConverter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.github.houbb.opencc4j.util.ZhConverterUtil;

/**
 * Traditional chinese transform to simple chinese
 * by opencc4j
 */
public class ChineseConverter {
    public enum TransferType {
        T_TO_S,
        S_TO_T
    }

    public static void convertFile(String absFilePath, TransferType type) {
        StringBuilder oldContentBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        FileWriter fileWriter = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(absFilePath));
            String line = bufferedReader.readLine();
            do {
                oldContentBuilder.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            } while (line != null);
            if (!("null" + System.lineSeparator()).equals(oldContentBuilder.toString())) {
                String newContent;
                if (type == TransferType.T_TO_S) {
                    newContent = ZhConverterUtil.convertToSimple(oldContentBuilder.toString());
                } else {
                    newContent = ZhConverterUtil.convertToTraditional(oldContentBuilder.toString());
                }

                fileWriter = new FileWriter(absFilePath);

                fileWriter.write(newContent);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileWriter != null)
                    fileWriter.close();
                if (bufferedReader != null)
                    bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void convertFolder(String folderPath, TransferType type) {
        FolderFileGetter.getFilePathList(folderPath).stream().forEach(filePath -> convertFile(filePath, type));
    }
}
