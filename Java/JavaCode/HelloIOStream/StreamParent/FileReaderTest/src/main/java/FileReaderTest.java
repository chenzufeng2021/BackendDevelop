import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author chenzufeng
 * @date 2021/11/7
 * @usage FileReaderTest
 */
public class FileReaderTest {
    private static Logger logger = LoggerFactory.getLogger(FileReaderTest.class);

    /**
     * 单个字符读取文件
     */
    @Test
    public void readFileMethod1() {
        String filePath = "D:\\Learning\\BackendDevelop\\Java\\JavaCode\\HelloIOStream\\IO流测试.txt";
        FileReader fileReader = null;
        int readData = 0;

        try {
            // 1. 创建FileReader对象
            fileReader = new FileReader(filePath);
            // 2. 循环读取数据：使用read()读取单个字符
            while ((readData = fileReader.read()) != -1) {
                logger.info("读取的数据为：{}", (char) readData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileReader != null) {
                    // 3. 关闭数据流
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 字符数组读取文件
     */
    @Test
    public void readFileMethod2() {
        String filePath = "D:\\Learning\\BackendDevelop\\Java\\JavaCode\\HelloIOStream\\IO流测试.txt";
        int readData = 0;
        char[] bytes = new char[16];
        FileReader fileReader = null;

        try {
            // 1. 创建FileReader对象
            fileReader = new FileReader(filePath);
            // 2. 循环读取：使用read(bytes)，返回实际读取到的字符数。如果返回-1，读取文件结束
            while ((readData = fileReader.read(bytes)) != -1) {
                logger.info("读取的数据为：{}", new String(bytes, 0, readData));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
