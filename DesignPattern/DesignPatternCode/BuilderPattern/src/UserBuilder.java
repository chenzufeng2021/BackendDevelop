/**
 * @author chenzufeng
 * @date 2021/12/7
 * @usage UserBuilder
 */
public class UserBuilder {
    private User user;

    private UserBuilder() {}

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public UserBuilder newPojo() {
        this.user = new User();
        return this;
    }

    public UserBuilder addId(String id) {
        this.user.setId(id);
        return this;
    }

    public UserBuilder addName(String name) {
        this.user.setName(name);
        return this;
    }

    public UserBuilder addAddress(String address) {
        this.user.setAddress(address);
        return this;
    }

    public User build() {
        return this.user;
    }
}
