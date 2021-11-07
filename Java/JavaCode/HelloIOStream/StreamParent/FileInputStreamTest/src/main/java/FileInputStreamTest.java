import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author chenzufeng
 * @date 2021/11/6
 * @usage FileInputStreamTest
 */
public class FileInputStreamTest {
    private static Logger logger = LoggerFactory.getLogger(FileInputStreamTest.class);

    public static void main(String[] args) throws IOException {

    }

    /**
     * 单个字节的读取，效率比较低
     * @throws IOException IOException
     */
    @Test
    public void readFileMethod1() throws IOException {
        String filePath = "D:\\Learning\\BackendDevelop\\Java\\JavaCode\\HelloIOStream\\IO流测试.txt";
        // 创建FileInputStream对象，用于读取文件
        FileInputStream fileInputStream = new FileInputStream(filePath);

        // 从该输入流读取一个字节的数据。如果没有输入，该方法终止；如果返回-1，表示读取完毕。
        int readData = fileInputStream.read();
        /*
         * int readLen = fileInputStream.read()——读到的是第一个字节的数据（I，对应73）
         * 那么readLen（73） != -1一直满足
         */
        while (readData != -1) {
            // 转成char显示——将72转为I
            logger.info("读取的数据：{}", (char) readData);
        }

        // 关闭文件流，释放资源
        fileInputStream.close();
    }

    /**
     * 单个字节的读取，效率比较低
     * @throws IOException IOException
     */
    @Test
    public void readFileMethod1_1() throws IOException {
        String filePath = "D:\\Learning\\BackendDevelop\\Java\\JavaCode\\HelloIOStream\\IO流测试.txt";
        int readData;
        // 创建FileInputStream对象，用于读取文件
        FileInputStream fileInputStream = new FileInputStream(filePath);

        // 从该输入流读取一个字节的数据。如果没有输入，该方法终止；如果返回-1，表示读取完毕。
        while ((readData = fileInputStream.read()) != -1) {
            // 转成char显示——将72转为I
            logger.info("读取的数据：{}", (char) readData);
        }

        // 关闭文件流，释放资源
        fileInputStream.close();
    }

    /**
     * 使用 read(byte[] b) 读取文件，提高效率
     * @throws IOException IOException
     */
    @Test
    public void readFileMethod2() throws IOException {
        String filePath = "D:\\Learning\\BackendDevelop\\Java\\JavaCode\\HelloIOStream\\IO流测试.txt";
        // 一次读取8个字节
        byte[] bytes = new byte[8];
        int readData;

        // 创建FileInputStream对象，用于读取文件
        FileInputStream fileInputStream = new FileInputStream(filePath);
        while ((readData = fileInputStream.read(bytes)) != -1) {
            logger.info("返回实际读取到字节数组中的字节数：{}", readData);
            logger.info("读取后的字节数组内容：{}", Arrays.toString(bytes));
            logger.info("读取的数据内容：{}", new String(bytes, 0, readData));
        }
        // 关闭文件流，释放资源
        fileInputStream.close();
    }
}
