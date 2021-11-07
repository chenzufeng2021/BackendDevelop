import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * @author chenzufeng
 * @date 2021/11/6
 * @usage FileCreate
 */
public class FileCreate {
    private static Logger logger = LoggerFactory.getLogger(FileCreate.class);

    public static void main(String[] args) throws IOException {
        // 当前设置只有在main函数中调用，才会保存log
    }

    /**
     * Creates a new File instance by converting the given pathname string into an abstract pathname.
     * @throws IOException IOException
     */
    @Test
    public void createMethod1() throws IOException {
        String pathName = "D:\\Learning\\BackendDevelop\\Java\\JavaCode\\HelloIOStream\\log\\createMethod1.txt";

        File file = new File(pathName);
        file.createNewFile();
        logger.info("createMethod1文件创建成功！");
    }

    /**
     * Creates a new File instance from a parent abstract pathname and a child pathname string.
     * @throws IOException IOException
     */
    @Test
    public void createMethod2() throws IOException {
        String parentPath = "D:\\Learning\\BackendDevelop\\Java\\JavaCode\\HelloIOStream\\log";
        String childPath = "createMethod2.txt";

        File file = new File(parentPath, childPath);
        file.createNewFile();
        logger.info("createMethod2文件创建成功！");
    }

    /**
     * Creates a new File instance from a parent abstract pathname and a child pathname string.
     * @throws IOException IOException
     */
    @Test
    public void createMethod3() throws IOException {
        String parentAbstractPathName = "D:\\Learning\\BackendDevelop\\Java\\JavaCode\\HelloIOStream\\log";
        File parentFile = new File(parentAbstractPathName);
        String childPathName = "createMethod3.txt";

        File file = new File(parentFile, childPathName);

        file.createNewFile();
        logger.info("createMethod3文件创建成功！");
    }

    /**
     * 获取文件信息
     */
    @Test
    public void getFileInfo() {
        // 先创建文件对象
        String filePath = "D:\\Learning\\BackendDevelop\\Java\\JavaCode\\HelloIOStream\\log\\createMethod1.txt";
        File file = new File(filePath);

        // 调用方法获取信息
        logger.info("文件名：{}", file.getName());
        logger.info("文件绝对路径：{}", file.getAbsoluteFile());
        logger.info("文件父级目录：{}", file.getParent());
        logger.info("文件大小（字节）：{}", file.length());
        logger.info("文件是否存在：{}", file.exists());
        logger.info("是不是一个文件：{}", file.isFile());
        logger.info("是不是一个目录：{}", file.isDirectory());
    }
}
