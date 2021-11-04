/**
 * @author chenzufeng
 * @date 2021/11/5
 * @usage 自动拆装箱与缓存
 */
public class AutoBoxOrUnBox {
    public static void main(String[] args) {
        Integer integer1 = 6;
        Integer integer2 = 6;

        if (integer1 == integer2) {
            System.out.println("integer1 == integer2");
        }

        if (integer1 != integer2) {
            System.out.println("integer1 != integer2");
        }

        Integer integer3 = 200;
        Integer integer4 = 200;

        if (integer3 == integer4) {
            System.out.println("integer1 == integer2");
        }

        if (integer3 != integer4) {
            System.out.println("integer1 != integer2");
        }
    }
}
