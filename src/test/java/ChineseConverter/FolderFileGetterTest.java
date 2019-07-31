package ChineseConverter;

import org.junit.Test;

public class FolderFileGetterTest {

    @Test
    public void getFilePathList() {
        FolderFileGetter.getFilePathList("D:\\iisi\\Project\\RILS\\code\\rils\\ekl\\platform-admin\\src" +
                "\\main\\webapp\\WEB-INF\\views\\ril").forEach(System.out::println);
    }
}