import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;

/**
 * @author chenzufeng
 * @date 2021/11/7
 * @usage FileWriterTest
 */
public class FileWriterTest {
    private static Logger logger = LoggerFactory.getLogger(FileWriterTest.class);

    public void writeFile() {
        String filePath = "D:\\Learning\\BackendDevelop\\Java\\JavaCode\\HelloIOStream\\IO流测试.txt";
        char[] chars = {'a', 'b', 'c'};
        // 1. 创建FileWriter对象
        FileWriter fileWriter = null;
        try {
            // 2. 默认覆盖写入
            fileWriter = new FileWriter(filePath);
            // 3.1 write(int c)：写入单个字符
            fileWriter.write('d');
            // 3.2 write(char cbuf[])：写入数组
            fileWriter.write(chars);
            // 3.3
            fileWriter
        }
    }
}
