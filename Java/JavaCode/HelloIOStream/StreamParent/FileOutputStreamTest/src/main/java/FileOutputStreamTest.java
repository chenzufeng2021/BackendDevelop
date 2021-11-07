import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author chenzufeng
 * @date 2021/11/6
 * @usage FileOutputStreamTest 将数据写到文件中
 */
public class FileOutputStreamTest {
    private static Logger logger = LoggerFactory.getLogger(FileOutputStreamTest.class);

    /**
     * 将数据写到文件中，如果该文件不存在，则创建
     * @throws IOException IOException
     */
    @Test
    public void writeFile() throws IOException {
        String filePath = "D:\\Learning\\BackendDevelop\\Java\\JavaCode\\HelloIOStream\\IO流测试.txt";
        // 1. 创建对象，写入文件路径
        FileOutputStream fileOutputStream = new FileOutputStream(filePath, true);
        String appendStr = "\nFileOutputStreamTest类";
        // 2. 字节数组写入输出流中（getBytes把字符串转换成字节数组）
        fileOutputStream.write(appendStr.getBytes());
        fileOutputStream.close();
    }

    /**
     * 拷贝图片
     * @throws IOException IOException
     */
    @Test
    public void copyPicture() throws IOException {
        String srcFilePath = "C:\\Users\\chenzufeng\\Pictures\\Saved Pictures\\冰山.jpg";
        String destFilePath = "D:\\Learning\\BackendDevelop\\Java\\JavaCode\\HelloIOStream\\冰山1.jpg";

        // 1. 创建输入流，读入文件
        FileInputStream fileInputStream = new FileInputStream(srcFilePath);
        // 2. 创建输出流，写入文件
        FileOutputStream fileOutputStream = new FileOutputStream(destFilePath);
        // 3. 定义字节数组，提高读取速度
        byte[] buffer = new byte[1024];

        int readData;
        while ((readData = fileInputStream.read(buffer)) != -1) {
            // 4. 读取到数据后，立即写入文件
            fileOutputStream.write(buffer, 0, readData);
            /*
             * public void write(byte b[], int off, int len) throws IOException {
             *      writeBytes(b, off, len, append);
             * }
             *
             * Writes len bytes from the specified byte array starting at offset off to this file output stream.
             * Params:
             *          b – the data.
             *          off – the start offset in the data.
             *          len – the number of bytes to write.
             * Throws:
             *          IOException – if an I/O error occurs.
             * "\n"算一个字节
             */
        }
        logger.info("数据拷贝成功！");

        // 5. 关闭输入、输出流，释放资源
        fileInputStream.close();
        fileOutputStream.close();
    }
}
