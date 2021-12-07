import java.util.StringJoiner;

/**
 * @author chenzufeng
 * @date 2021/11/26
 * @usage Model
 */
public class Model {
    /**
     * 定一个Boolean类型的success成员变量
     */
    private Boolean success;

    /**
     * 定一个boolean类型的failure成员变量
     */
    private boolean failure;

    /**
     * 覆盖toString方法，使用Java 8 的StringJoiner
     */
    @Override
    public String toString() {
        return new StringJoiner(", ", Model.class.getSimpleName() + "[", "]")
                .add("success=" + success)
                .add("failure=" + failure)
                .toString();
    }
}
