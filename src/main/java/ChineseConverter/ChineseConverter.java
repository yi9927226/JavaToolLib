package ChineseConverter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.github.houbb.opencc4j.util.ZhConverterUtil;

public class ChineseConverter {
    public static void convertFile(String absFilePath) {
        StringBuilder oldContent = new StringBuilder();
        BufferedReader bufferedReader = null;
        FileWriter fileWriter = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(absFilePath));
            String line = bufferedReader.readLine();
            do {
                oldContent.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            } while (line != null);

            String newContent = ZhConverterUtil.convertToSimple(oldContent.toString());

            fileWriter = new FileWriter(absFilePath);

            fileWriter.write(newContent);

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
}
