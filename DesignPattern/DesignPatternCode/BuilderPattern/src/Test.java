/**
 * @author chenzufeng
 * @date 2021/12/7
 * @usage Test
 */
public class Test {
    public static void main(String[] args) {
        User user = UserBuilder.builder()
                .newPojo()
                .addId("1")
                .addName("chenzufeng")
                .addAddress("hangzhou")
                .build();
        System.out.println(user);
    }
}
