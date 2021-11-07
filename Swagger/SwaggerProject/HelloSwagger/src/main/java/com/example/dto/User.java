package com.example.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author chenzufeng
 * @date 2021/10/12
 * @usage User
 */
@ApiModel(value = "实体类：User")
public class User {
    @ApiModelProperty(value = "用户名", required = true)
    private String userName;
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
